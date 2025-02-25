import React from "react";

const Search: React.FC = () => {
    return (
        <form className={"bg-gray-100 shadow-md rounded"}>
            <input
                className={"p-1 text-sm focus:outline-none"}
                id={'search'}
                type={'text'}
                placeholder={'Recherche'}
            />
        </form>
    )
}

export default Search