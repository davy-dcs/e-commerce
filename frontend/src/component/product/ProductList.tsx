import React, {useEffect, useState} from "react";
import axios from "axios";
import AddProduct from "./AddProduct.tsx";

type ProductListProps = {
    type: string;
}

interface ProductResponse {
    reference: string;
    name: string;
    price: number;
    paymentTerms: string;
    image: string;
    type: string;
}

const ProductList: React.FC<ProductListProps> = (props) => {
    const [productList, setProductList] = useState<ProductResponse[] | null>(null)
    const [productDetails, setProductDetails] = useState<ProductResponse | null>(null)

    const auth = {
        username: "Davy",
        password: "password",
    }
    const requestBody = {
        productType: props.type,
    }

    useEffect(() => {
        axios.post<ProductResponse[]>("http://localhost:8080/api/v1/products", requestBody, { auth })
            .then(response => {
                setProductList(response.data);
            })
            .catch((error) => {console.error("Erreur lors de la récupération du panier :", error)});
    }, [props.type])
    return (
        <div className={"flex-initial w-full grid grid-cols-3"}>
            {productList?.length === 0 ?
                (
                    <div  className="p-4 text-sm text-yellow-800 rounded-lg bg-yellow-50">
                        <span className="font-medium">Liste des {props.type} est vide.</span>
                    </div>
                ) :
                (
                    productList?.map((product: ProductResponse) => (
                        <div key={product.reference} className="rounded overflow-hidden shadow-lg">

                            <img className="px-6 py-4 mx-auto w-60 h-60" src={product.image} alt={product.name}/>

                            <div className="px-6 py-4">
                                <div className={"flex w-full items-center justify-around"}>
                                    <div className="font-bold text-xl mb-2">{product.name}</div>
                                    <p className="text-gray-700 text-base">
                                        €{product.price}
                                        {product.paymentTerms === "UNIT_PRICE" ? (
                                            "/pièce"
                                        ) : (
                                            "/kg"
                                        )}
                                    </p>
                                </div>
                            </div>
                            <div className="px-6 py-4 flex items-center justify-center">
                                <button className={"bg-amber-400 px-2 py-1 rounded text-sm"} onClick={() => setProductDetails(product)}>Voir produit</button>
                            </div>
                        </div>
                    ))
                )
            }
            {productDetails && (
                <div className="fixed inset-0 flex items-center justify-center bg-gray-400/40">
                    <div className="bg-white p-6 rounded-lg shadow-lg">
                        <h2 className="text-xl font-semibold mb-2">{productDetails.name}</h2>
                        <p className="mb-4">€{productDetails.price} {productDetails.paymentTerms === "UNIT_PRICE" ? (
                            "/pièce"
                        ) : (
                            "/kg"
                        )}</p>
                        <AddProduct productRef={productDetails.reference}/>
                        <button className="border border-amber-500 text-amber-500 hover:bg-amber-500 hover:text-white cursor-pointer px-4 py-2 rounded-md" onClick={() => setProductDetails(null)}>
                            Fermer
                        </button>
                    </div>
                </div>
            )}
        </div>
    )
}
export default ProductList