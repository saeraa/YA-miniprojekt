import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
// import ProductTable from './ProductTable';
import settings from './properties/settings.json';
import ProductItems from './ProductItems';
import ProductChart from './ProductChart';

class App extends Component {

  inputElement = React.createRef()
  constructor() {
    super()
    this.state = {
      originalProducts: [],
      products: [],
      chartProducts: [],
      subTotal: 0.0,
      currentProduct: {
        text: '',
        key: '',
      },
      search: '',
      customerId: 'TORTU'
    }
    this.url = settings.api_url + ':' + settings.api_port;
  }

  componentDidMount() {
    fetch(this.url + '/products')
    .then(results => {
        return results.json();
    }).then(data => {
        this.setState({products: data});
        this.setState({originalProducts: data});
        console.log("state", this.state.products);
    })
  }

  updateSearch(event){
    this.setState({search: event.target.value});
    let filteredProducts = this.state.originalProducts.filter(
          (item) => { 
              return item.productName.toLowerCase().indexOf(event.target.value.toLowerCase()) !== -1;
          } 
      );
    this.setState({products: filteredProducts});
  }

  buyItem = key => {
    let chartAddedProducts = this.state.chartProducts;
    chartAddedProducts.push(key);
    this.setState({chartProducts: chartAddedProducts})
    let updatedSubTotal = this.state.subTotal + key.unitPrice;
    this.setState({subTotal: updatedSubTotal})
  }

  render() {
    return (
      <div className="App">
      <header className="App-header">
          <h2>
          Buy from Company.com NOW!
          </h2>
          <div>
            <h3>Your chart ({this.state.customerId}):</h3>
            <div className="chart">
            <ProductChart chartProducts={this.state.chartProducts} subTotal={this.state.subTotal} />
            </div>
          </div>
          <div id="search-div">
          <label htmlFor="customer-search">Search product</label>
          <input id="customer-search" type="text" value={this.state.search}  onChange={this.updateSearch.bind(this)}/>
          </div>
          <div className="rTable">
            <div className="rTableRow">
                <div className="rTableHead">ProductID</div>
                <div className="rTableHead">ProductName</div>
                <div className="rTableHead">Quantity per unit</div>
                <div className="rTableHead">Unit price</div>
                <div className="rTableHead"></div>
            </div>
          <ProductItems entries={this.state.products} buyItem={this.buyItem} />
          </div>
      </header>
     </div>    
    );
  }
}

export default App;
