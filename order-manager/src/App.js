import React, { Component } from 'react'
import './App.css'
// import OrderList from './OrderList'
import OrderItems from './OrderItems'
import settings from './properties/settings.json';

class App extends Component {
  inputElement = React.createRef()
  constructor() {
    super()
    this.state = {
      originalOrders: [],
      orders: [],
      currentOrder: {
        text: '',
        key: '',
      },
      search: ''
    }
    this.url = settings.api_url + ':' + settings.api_port;
  }

  componentDidMount() {
    fetch(this.url + '/orders')
    .then(results => {
        return results.json();
    }).then(data => {
        this.setState({orders: data});
        this.setState({originalOrders: data});
    })  
  }
  
  deleteItem = key => {
    const filteredItems = this.state.orders.filter(order => {
      return order.orderID !== key
    })
    this.setState({
      orders: filteredItems,
      originalOrders: filteredItems
    })
    fetch(this.url + '/deleteOrder/' + key)
    .catch(function() {
        console.log("error");
    });
  }

  editOrder = key => {
    console.log('Edit: ' + key)
  }

  handleInput = e => {
    const itemText = e.target.value
    const currentOrder = { text: itemText, key: Date.now() }
    this.setState({
      currentOrder,
    })
  }

  addItem = e => {
    e.preventDefault()
    const newItem = this.state.currentOrder
    if (newItem.text !== '') {
      const orders = [this.state.orders, newItem]
      this.setState({
        orders: orders,
        currentOrder: { text: '', key: '' },
      })
    }
  }

  updateSearch(event){
    this.setState({search: event.target.value});
    let filteredOrders = this.state.originalOrders.filter(
          (item) => { 
              return item.customerID.indexOf(event.target.value) !== -1;
          } 
      );
    this.setState({orders: filteredOrders});
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1>
            All order
          </h1>
          <div id="search-div">
          <label htmlFor="customer-search">Search customer</label>
          <input id="customer-search" type="text" value={this.state.search}  onChange={this.updateSearch.bind(this)}/>
          </div>
          <div className="rTable">
            <div className="rTableRow">
                <div className="rTableHead">OrderID</div>
                <div className="rTableHead">CustomerID</div>
                <div className="rTableHead">Order date</div>
                <div className="rTableHead">Shipping date</div>
                <div className="rTableHead"></div>
                <div className="rTableHead"></div>
            </div>
          <OrderItems entries={this.state.orders} deleteItem={this.deleteItem} editOrder={this.editOrder} />
          </div>
        </header>
      </div>
    )
  }
}

export default App
