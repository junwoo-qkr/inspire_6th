import Book from "../../components/sample/Book";

const LibraryPage = () => {
    // Script
    const books = [
        {category: 'IT', bookName: 'Java', price: 10000},
        {category: 'IT', bookName: 'Python', price: 12000},
        {category: 'lang', bookName: 'KOR', price: 7000},
        {category: 'lang', bookName: 'ENG', price: 8000},
        {category: 'food', bookName: 'Italian', price: 22000},
        {category: 'food', bookName: 'British', price: 15000},
    ];


    // UI
    return (
        <div>
            {
                books
                    .filter(book => book.category === "lang")
                    .map((book, idx) => {
                        return <Book bookName={book.bookName}/>
                    }
                )
            }
        </div>
    );
}

export default LibraryPage;