import React, { useEffect, useMemo, useState } from "react";
import BackgroundLeft from "../components/background-left";
import "../styles/pages/emprestimo.css";
import { EmprestimoRequest, LivroResponse } from "../@types/types";
import api from "../util/axios";

function Emprestimo() {
    const [newLoan, setNewLoan] = useState<EmprestimoRequest>({} as EmprestimoRequest);
    const [selectedBooks, setSelectedBooks] = useState<LivroResponse[]>([]);
    const [allBooks, setAllBooks] = useState<LivroResponse[]>([]);
    const [isModalSelectBooksOpen, setIsModalSelectBooksOpen] = useState<boolean>(false);
    const [search, setSearch] = useState<string>("");
    const [doSearch, setDoSearch] = useState<boolean>(false);

    const filteredBooks = useMemo(() => {
        return allBooks?.filter((book) => {
          return book.id.toString().includes(search) || book.titulo.titulo.toLowerCase().includes(search.toLowerCase());
        });
      }, [allBooks,doSearch]);

    const handleNewLoan = () => {
        console.log(newLoan);
        console.log(selectedBooks);
        api.post('/emprestimo', {
            dataEmprestimo: newLoan.dataEmprestimo,
            itensEmprestimo: selectedBooks,
            alunoMatricula: newLoan.alunoMatricula
        })
        .then((response) => {
            console.log(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }

    useEffect(() => {
        api.get('/livro')
            .then((response) => {
                setAllBooks(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    return (
        <div className="emprestimo-background">
            <BackgroundLeft page="Emprestimo" />
            <div className="emprestimo-background-right">
                <div className="emprestimo-container">
                    <h1>Informe os dados do emprestimo</h1>
                    <div className="emprestimo-input-container">
                        <label className="emprestimo-label">Aluno</label>
                        <input  type="text" 
                                placeholder="Digite o RA do aluno" 
                                className="emprestimo-input" 
                                value={newLoan.alunoMatricula}
                                onChange={(e: React.FormEvent) => {setNewLoan({...newLoan, alunoMatricula: (e.target as HTMLInputElement).value})}}
                        />
                    </div>

                    <div className="emprestimo-input-container">
                        <label className="emprestimo-label">Data</label>
                        <input  type="date" 
                                placeholder="Digite a data do emprestimo" 
                                className="emprestimo-input" 
                                value={newLoan.dataEmprestimo}
                                onChange={(e: React.FormEvent) => {setNewLoan({...newLoan, dataEmprestimo: (e.target as HTMLInputElement).value})}}
                        />
                    </div>

                    <div className="emprestimo-input-container">
                        <label className="emprestimo-label">Livros</label>
                        {selectedBooks.length > 0 ?
                            selectedBooks.map((book) => {
                                return (
                                    <div key={book.id} className="emprestimo-selected-books">
                                        <label>{book.titulo.titulo}</label>
                                        <button onClick={() => {
                                            setSelectedBooks(selectedBooks.filter((selectedBook) => selectedBook.id !== book.id));
                                        }}>X</button>
                                    </div>
                                );
                                })
                            : <label className="emprestimo-label-livros">Nenhum livro selecionado</label>
                        }
                    </div>

                    <div className="emprestimo-button-container">
                        <button className="emprestimo-button" onClick={() => setIsModalSelectBooksOpen(!isModalSelectBooksOpen)}>Livros</button>
                        <button className="emprestimo-button" onClick={handleNewLoan}>Emprestar</button>
                    </div>
                </div>
                <div className={isModalSelectBooksOpen ? "emprestimo-modal" : "emprestimo-modal-hidden"}>
                    <div className="emprestimo-modal-content">
                        <div className="emprestimo-modal-header">
                            <div className="emprestimo-modal-title-container">
                                <h1>Selecione os livros</h1>
                            </div>
                            <div className="emprestimo-modal-close-container">
                                <label onClick={() => setIsModalSelectBooksOpen(!isModalSelectBooksOpen)}> X </label>
                            </div>
                        </div>
                        <div className="emprestimo-input-search-container">
                            <input  type="text" 
                                    placeholder="Pesquisar títulos" 
                                    className="emprestimo-input-search" 
                                    value={search}
                                    onChange={(e: React.FormEvent) => setSearch((e.target as HTMLInputElement).value)}
                            />
                            <button className="emprestimo-button-search"
                                    onClick={() => setDoSearch(!doSearch)}>
                                        Pesquisar
                            </button>
                        </div>
                        <div className="emprestimo-modal-books-container">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Disponível</th>
                                        <th>Exemplar</th>
                                        <th>ISBN</th>
                                        <th>Título</th>
                                        <th>Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {(filteredBooks?.length || 0) > 0 ? 
                                    filteredBooks.map((book) => (
                                        <tr key={book.id}>
                                            <td>{book.id}</td>
                                            <td>{book.disponivel == true ? "Sim" : "Não"}</td>
                                            <td>{book.exemplarBiblioteca == true ? "Sim" : "Não"}</td>
                                            <td>{book.titulo.isbn}</td>
                                            <td>{book.titulo.titulo}</td>
                                            <td>
                                                <button onClick={() => {
                                                    setSelectedBooks([...selectedBooks, book]);
                                                }}>Selecionar</button>
                                            </td>
                                        </tr>
                                    )) :
                                    <tr>
                                        <td colSpan={4}>Nenhum livro encontrado</td>
                                    </tr>
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Emprestimo;