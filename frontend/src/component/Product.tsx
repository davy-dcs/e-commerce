import React from "react";

type ProductProps = {
    name: string,
    price: number,
    description: string,
    stock: number
}

const Product: React.FC<ProductProps> = (props) => {
    return (
        <>
            <p>{props.name}</p>
            <p>{props.price}€</p>
            <p>{props.description}</p>
            <p>{props.stock} en stock</p>
        </>
    )
}
export default Product