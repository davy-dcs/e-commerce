import React from "react"

const Search = () => {
    return(
        <React.Fragment>
            <input placeholder={'Search...'} type={'text'}/>
            <input type={'checkbox'} id={'inStock'}/><label htmlFor={'inStock'}>Only show products in stock</label>
        </React.Fragment>
    )
}
export default Search