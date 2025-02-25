import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const auth = {
    username: "Davy",
    password: "password",
}

// Action pour récupérer le panier (GET)
export const fetchCart = createAsyncThunk("cart/fetchCart", async () => {
    const response = await axios.get("http://localhost:8080/api/v1/carts", {auth});
    return response.data;
});

// Action pour ajouter un produit (POST)
export const addToCart = createAsyncThunk(
    "cart/addToCart",
    async (productCart: { productReference: string; quantity: number }, { dispatch }) => {
        await axios.post("http://localhost:8080/api/v1/product-cart", productCart, {auth});
        dispatch(fetchCart()); // Recharger le panier après ajout
    }
);

// Action pour modifier la quantité d'un produit (PUT)
export const updateQuantity = createAsyncThunk(
    "cart/updateQuantity",
    async (productCartQuantity: {uuid: string; quantity: number}, {dispatch}) => {
        await axios.put("http://localhost:8080/api/v1/product-cart", productCartQuantity, {auth});
        dispatch(fetchCart()); // Recharger le panier après la modification
    }
);

// Action pour supprimer un produit (DELETE)
export const deleteProductCart = createAsyncThunk(
    "cart/deleteProductCart",
    async (_uuid: string, { dispatch }) => {
        await axios.delete("http://localhost:8080/api/v1/product-cart/" + _uuid, {auth});
        dispatch(fetchCart()); // Recharger le panier après ajout
    }
);

type CartState = {
    uuid: string | null;
    date: string | null;
    status: string | null;
    productCarts: ProductCarts[];
}
type ProductCarts = {
    uuid: string | null;
    product: Product;
    quantity: number | null;
}
type Product = {
    reference: string | null;
    name: string | null;
    price: number | null;
    paymentTerms: string | null;
    image: string | null;
    type: string | null;
}

const initialState: {
    cart: CartState;
    loading: boolean;
    error: string | null;
} = {
    cart: {
        uuid: null,
        date: null,
        status: null,
        productCarts: []
    },
    loading: false,
    error: null
}

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchCart.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchCart.fulfilled, (state, action) => {
                state.loading = false;
                state.cart = action.payload;
            })
            .addCase(fetchCart.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload as string;
            });
    },
});

export default cartSlice.reducer;