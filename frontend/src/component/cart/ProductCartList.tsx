import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../../store.tsx";
import {deleteProductCart, fetchCart} from "./cartSlice.tsx";
import ProductCartQuantity from "../form/ProductCartQuantity.tsx";

const ProductCartList: React.FC = () => {
    const dispatch = useDispatch<AppDispatch>();
    const { cart, loading, error } = useSelector((state: RootState) => state.cart);

    useEffect(() => {
        dispatch(fetchCart());
    }, [dispatch])

    return (
        <div className={"flex-initial grid grid-cols-6 gap-3"}>
            <h3 className={"font-bold text-sm text-center"}>Produit</h3>
            <h3 className={"font-bold text-sm text-center"}>Référence</h3>
            <h3 className={"font-bold text-sm text-center"}>Prix</h3>
            <h3 className={"font-bold text-sm text-center"}>Quantité</h3>
            <h3 className={"font-bold text-sm text-center"}>Total</h3>
            <h3 className={"text-transparent"}>Supprimer</h3>
            {loading !== true && error === null && cart && cart.productCarts.length > 0 ?
                    cart.productCarts.map((product) => (
                        <React.Fragment key={product.product.reference}>
                            <img src={product.product.image} alt={product.product.name} className={"size-20 mx-auto"} />
                            <p className={"m-auto font-extralight text-gray-500"}>{product.product.reference}</p>
                            <p className={"m-auto text-gray-500"}>€{product.product.price}{product.product.paymentTerms === "UNIT_PRICE" ? (
                                "/pièce"
                            ) : (
                                "/kg"
                            )}</p>
                            <ProductCartQuantity quantity={product.quantity} uuid={product.uuid} />
                            <p className={"m-auto font-medium text-gray-500"}>{product.product.price * product.quantity}</p>
                            <button onClick={() => dispatch(deleteProductCart(product.uuid))}>
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-10 cursor-pointer fill-red-400 hover:fill-red-600 stroke-white">
                                    <path strokeLinecap="round" strokeLinejoin="round" d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                                </svg>
                            </button>
                        </React.Fragment>
                    ))
                     : null
            }
        </div>
    )
}
export default ProductCartList