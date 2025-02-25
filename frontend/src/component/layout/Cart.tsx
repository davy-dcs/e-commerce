import React from "react";
import ProductCartList from "../cart/ProductCartList.tsx";
import Receipt from "../cart/Receipt.tsx";

const Cart: React.FC = () => {
    return (
        <main className={"flex w-7xl mx-auto my-10"}>
            <ProductCartList />
            <Receipt />
        </main>
    )
}
export default Cart