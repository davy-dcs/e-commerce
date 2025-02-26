import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../../store.tsx";
import {fetchCart, updateStatus} from "./cartSlice.tsx";

const Receipt: React.FC = () => {
    const dispatch = useDispatch<AppDispatch>();
    const { cart, loading, error } = useSelector((state: RootState) => state.cart);

    useEffect(() => {
        dispatch(fetchCart());
    }, [dispatch])

    const getTotalPrice:() => string = () => {
        let price = 0;
        for (const productCart of cart.productCarts) {
            price += productCart.quantity * productCart.product.price
        }
        return parseFloat(String(price)).toFixed(2);
    }

    return (
        <aside className={"w-64"}>
            <div className={"bg-gray-200 rounded shadow-inner border border-gray-300"}>
                <h3 className={"p-4"}>Résumé</h3>
                <hr className={"text-gray-400"}/>
                <h4 className={"p-4"}>Liste d'articles</h4>
                {loading !== true && error === null && cart && cart.productCarts.length > 0 ? (
                    cart.productCarts.map((product) => (
                        <div key={product.uuid} className={"flex items-center justify-between px-4 py-2"}>
                            <p>{product.quantity} x </p>
                            <p>{product.product.name} </p>
                            <p>{product.product.price}</p>
                        </div>
                    ))
                ) : (<p className={"text-sm text-center pb-4"}>Aucun produit ajouté dans le panier</p>)}
                <hr className={"text-gray-400"}/>
                <div className={"p-4 flex items-center justify-between"}>
                    <h4 className={""}>Total</h4>
                    <p>€{getTotalPrice()}</p>
                </div>

            </div>
            <button onClick={() => dispatch(updateStatus({uuid: cart.uuid, status: "VALIDATED"}))} disabled={cart.productCarts.length < 1} type="button" className="disabled:bg-gray-400 disabled:cursor-default cursor-pointer w-full px-5 py-2.5 my-1 text-sm font-medium text-white bg-red-400 hover:bg-red-600 focus:ring-4 focus:outline-none focus:ring-red-300 rounded-lg text-center">
                Valider
            </button>

        </aside>
    )
}
export default Receipt