import React, { Component } from 'react'

class ProductItems extends Component {
  createTasks = product => {
    return (
      <div className="rTableRow" key = {product.productID}>
        <div className="rTableCell">{product.productID}</div>
        <div className="rTableCell">{product.productName}</div>
        <div className="rTableCell">{product.quantityPerunit}</div>
        <div className="rTableCell">{product.unitPrice}</div>
        <div className="rTableCell clickable" onClick={() => this.props.buyItem(product)}>Buy</div>
      </div>
    )
  }
  render() {
    const productEntries = this.props.entries
    const listItems = productEntries.map(this.createTasks)
    
    return listItems
  }
}

export default ProductItems