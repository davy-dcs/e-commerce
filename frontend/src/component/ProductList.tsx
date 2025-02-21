import React from "react";
import Search from "./Search.tsx";
import List from "./List.tsx";

const ProductList = () => {
    const products = [
        {category: "Sporting Goods", price: "$49.99", stocked: true, name: "Football"},
        {category: "Sporting Goods", price: "$9.99", stocked: true, name: "Baseball"},
        {category: "Sporting Goods", price: "$29.99", stocked: true, name: "Basketball"},
        {category: "Electronics", price: "$99.99", stocked: true, name: "iPod Touch"},
        {category: "Electronics", price: "$399.99", stocked: true, name: "iPhone 5"},
        {category: "Electronics", price: "$199.99", stocked: true, name: "Nexus 7"}
    ];
    return (
        <React.Fragment>
            <Search />
            <List products={products} />
        </React.Fragment>
    )
}
export default ProductList