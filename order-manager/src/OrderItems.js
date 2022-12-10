import React, { Component } from 'react'
import Moment from 'react-moment';

class OrderItems extends Component {
  createTasks = order => {
    return (
      <div className="rTableRow" key = {order.orderID}>
        <div className="rTableCell">{order.orderID}</div>
        <div className="rTableCell">{order.customerID}</div>
        <div className="rTableCell">
            <Moment format="YYYY-MM-DD">{order.orderDate}</Moment>
        </div>
        <div className="rTableCell">
            <Moment format="YYYY-MM-DD">{order.shippedDate}</Moment>
        </div>
        <div className="rTableCell clickable" onClick={() => this.props.editOrder(order.orderID)}>Edit</div>
        <div className="rTableCell clickable" onClick={() => this.props.deleteItem(order.orderID)}>Delete</div>
        </div>
    )
  }
  render() {
    const todoEntries = this.props.entries
    const listItems = todoEntries.map(this.createTasks)
    
    return listItems
  }
}

export default OrderItems