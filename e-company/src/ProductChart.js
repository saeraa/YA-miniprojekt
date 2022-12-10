import React, { Component } from 'react'

class ProductChart extends Component {

    createTasks = product => {
        return (
            <div key={product.productID}>{product.productName + " : " + product.unitPrice}</div>
            // <div className="rTableRow" key = {product.productID}>
            // <div className="rTableCell">{product.productID}</div>
            // <div className="rTableCell">{product.productName}</div>
            // <div className="rTableCell">{product.quantityPerunit}</div>
            // <div className="rTableCell">{product.unitPrice}</div>
            // <div className="rTableCell clickable" onClick={() => this.props.buyItem(product.productID)}>Buy</div>
        )
    }    

    render(){
        const productEntries = this.props.chartProducts;
        const listItems = productEntries.map(this.createTasks)
        const subTotal = this.props.subTotal;
        return(
            <div>
                <div>{listItems}</div>
                <hr></hr>
                <div>{"Total: " + subTotal}</div>
            </div>
        ) 
    }

}

export default ProductChart
