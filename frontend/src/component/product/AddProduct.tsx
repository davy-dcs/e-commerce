import React from "react";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as yup from "yup";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../store.tsx";
import {addToCart} from "../cart/cartSlice.tsx";

type AddProductProps = {
    productRef: string
}

const AddProduct: React.FC<AddProductProps> = (props) => {
    const dispatch = useDispatch<AppDispatch>();

    const initialValues = {
        productReference: props.productRef,
        quantity: 1.0
    }
    const validationSchema = yup.object().shape({
        productReference: yup.string().required("Référence produit manquant"),
        quantity: yup.number().moreThan(0, "Quantité doit être positif").required("Quantité non valide")
    })

    const submit = (values: {productReference: string, quantity: number}) => {
        dispatch(addToCart(values))
    }
    return (
        <Formik initialValues={initialValues} onSubmit={submit} validationSchema={validationSchema}>
            <Form className={"my-3 flex items-center justify-around w-full space-x-3"}>
                <Field name={"productReference"} hidden />
                <ErrorMessage name={"productReference"} />
                <Field name={"quantity"} type="number" step="0.5" className="appearance-none [&::-webkit-inner-spin-button]:hidden [&::-webkit-outer-spin-button]:hidden h-8 w-16"/>
                <ErrorMessage name={"quantity"} />
                <button type="submit" className={"bg-amber-500 hover:bg-amber-600 text-white cursor-pointer py-2 px-4 rounded"}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-8">
                        <path strokeLinecap="round" strokeLinejoin="round" d="m20.25 7.5-.625 10.632a2.25 2.25 0 0 1-2.247 2.118H6.622a2.25 2.25 0 0 1-2.247-2.118L3.75 7.5m8.25 3v6.75m0 0-3-3m3 3 3-3M3.375 7.5h17.25c.621 0 1.125-.504 1.125-1.125v-1.5c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125Z" />
                    </svg>
                </button>
            </Form>
        </Formik>
    )
}
export default AddProduct