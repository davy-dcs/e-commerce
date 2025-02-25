import * as yup from "yup";
import {updateQuantity} from "../cart/cartSlice.tsx";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../../store.tsx";
import {ErrorMessage, Field, Form, Formik} from "formik";
import React, {useState} from "react";

type ProductCartQuantityProps = {
    quantity: number,
    uuid: string
}

const ProductCartQuantity: React.FC<ProductCartQuantityProps> = (props) => {
    const dispatch = useDispatch<AppDispatch>();
    const [operation, setOperation] = useState(false)

    const initialValues = {
        uuid: props.uuid,
        quantity: props.quantity
    }
    const validationSchema = yup.object().shape({
        uuid: yup.string().required("Uuid produit cart manquant"),
        quantity: yup.number().required("Quantité non valide")
    })

    const submit = () => {
        if (operation) {
            dispatch(updateQuantity(
                {
                    uuid: props.uuid,
                    quantity: props.quantity + 1
                }
            ))
        } else {
            dispatch(updateQuantity({
                uuid: props.uuid,
                quantity: props.quantity - 1
            }))
        }
    }

    return (
        <Formik initialValues={initialValues} onSubmit={submit} validationSchema={validationSchema}>
            <Form className={"my-3 flex items-center justify-around w-full space-x-3"}>
                <button onClick={() => setOperation(false)}
                        className={"bg-slate-500 hover:bg-slate-600 text-white cursor-pointer py-2 px-4 rounded"}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                         stroke="currentColor" className="size-3">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M5 12h14"/>
                    </svg>
                </button>
                <Field name={"quantity"} type="number" step="0.5" value={initialValues.quantity}
                       className="appearance-none [&::-webkit-inner-spin-button]:hidden [&::-webkit-outer-spin-button]:hidden h-8 w-6 text-center text-gray-500"
                       readOnly/>
                <ErrorMessage name={"quantity"}/>
                <button onClick={() => setOperation(true)}
                        className={"bg-slate-500 hover:bg-slate-600 text-white cursor-pointer py-2 px-4 rounded"}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                         stroke="currentColor" className="size-3">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15"/>
                    </svg>
                </button>
            </Form>
        </Formik>
    )
}
export default ProductCartQuantity