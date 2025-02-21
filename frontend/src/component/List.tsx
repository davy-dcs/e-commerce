import React from "react"

type ListProps = {
    products: {
        category: string,
        price: string,
        stocked: boolean,
        name: string
    }[]
}
const List: React.FC<ListProps> = (props) => {
    return (
        <ul>
            {props.products.map((product, index) => (
                <li key={index}>
                    {product.name} {product.price}
                </li>
            ))}
        </ul>
    )
}
export default List