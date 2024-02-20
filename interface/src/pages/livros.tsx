import React, { useEffect, useMemo, useState } from 'react';
import '../styles/pages/livros.css';
import BackgroundLeft from '../components/background-left';
import { LivroRequest, LivroResponse, Titulo } from '../@types/types';
import api from '../util/axios';

function Livros() {
    const [newBook, setNewBook] = useState<LivroRequest>({} as LivroRequest);
    const [books, setBooks] = useState<LivroResponse[]>([]);
    const [titulos, setTitulos] = useState<Titulo[]>([]);
    const [search, setSearch] = useState<string>("");
    const [doSearch, setDoSearch] = useState<boolean>(false);
    const [updateTable, setUpdateTable] = useState<boolean>(false);

    const filteredBooks = useMemo(() => {
        return books?.filter((book) => {
          return book.id.toString().includes(search) || book.titulo.titulo.toLowerCase().includes(search.toLowerCase());
        });
      }, [books,doSearch]);

    const handleNewBook = () => {
        console.log(newBook);
        api.post('/livro', {
            disponivel: newBook.disponivel,
            exemplarBiblioteca: newBook.exemplarBiblioteca,
            titulo: {
                id: newBook.titulo.id,
                prazo: newBook.titulo.prazo,
                isbn: newBook.titulo.isbn,
                titulo: newBook.titulo.titulo
            }
        })
        .then((response) => {
            setUpdateTable(!updateTable);
            handleClear();
        })
        .catch((error) => {
            console.log(error);
        });
    }

    const handleClear = () => {
        setNewBook({
            disponivel: false,
            exemplarBiblioteca: false,
            titulo: {
                id: 0,
                prazo: 0,
                isbn: "",
                titulo: ""
            }
        });
    };

    useEffect(() => {
        api.get('/livro')
            .then((response) => {
                setBooks(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

        api.get('/titulo')
            .then((response) => {
                setTitulos(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

    }, [updateTable]);

    return (
        <div className="livros-background">
            <BackgroundLeft page="Livros" />
            <div className="livros-background-right">
                <div className="livros-content-up">
                    <div className="livros-title-container">
                        <h1>Cadastro de Livros</h1>
                    </div>
                    <div className="livros-content-container">
                        <div className="livros-inputs-container">
                            <div className="livros-input-container">
                                <label className="livros-label">Disponível</label>
                                <div className="livros-radio-container">
                                    <label>
                                        <input type="radio" id="disponivel" name="disponivel" onClick={() => setNewBook({...newBook, disponivel: true})}/>
                                        Sim
                                    </label>
                                    <label>
                                        <input type="radio" id="disponivel" name="disponivel" onClick={() => setNewBook({...newBook, disponivel: false})}/>
                                        Não
                                    </label>
                                </div>
                            </div>
                            <div className="livros-input-container">
                                <label className="livros-label">Exemplar</label>
                                <div className="livros-radio-container">
                                    <label>
                                        <input type="radio" id="exemplar" name="exemplar" onClick={() => setNewBook({...newBook, exemplarBiblioteca: true})}/>
                                        Sim
                                    </label>
                                    <label>
                                        <input type="radio" id="exemplar" name="exemplar" onClick={() => setNewBook({...newBook, exemplarBiblioteca: false})}/>
                                        Não
                                    </label>
                                </div>
                            </div>
                            <div className="livros-input-container">
                                <label className="livros-label">Título</label>
                                <select className="livros-input" 
                                        required 
                                        defaultValue="" 
                                        onChange={(e: React.ChangeEvent<HTMLSelectElement>) => {
                                            const tituloSelecionado = titulos?.find((titulo) => titulo.id === parseInt(e.target.value));
                                            if (tituloSelecionado) {
                                                setNewBook({...newBook, titulo: tituloSelecionado});
                                            }
                                        }}
                                >
                                    <option value="" disabled>Selecione...</option>
                                    {titulos?.map((titulo) => 
                                        <option key={titulo.id} 
                                                value={titulo.id} 
                                        >{titulo.titulo}
                                        </option>
                                    )}
                                </select>
                            </div>
                            
                        </div>
                        <div className="livros-options-container">
                            <button className="livros-button" onClick={handleClear}>Limpar</button>
                            <button className="livros-button" onClick={handleNewBook}>Cadastrar</button>
                        </div>
                    </div>    
                </div>
                <div className="livros-content-bottom">
                    <div className="livros-title-container">
                        <h1>Listagem de Livros</h1>
                    </div>
                    <div className="livros-input-search-container">
                        <input  type="text" 
                                placeholder="Pesquisar títulos" 
                                className="livros-input-search" 
                                value={search}
                                onChange={(e: React.FormEvent) => setSearch((e.target as HTMLInputElement).value)}
                        />
                        <button className="livros-button-search"
                                onClick={() => setDoSearch(!doSearch)}>
                                    Pesquisar
                        </button>
                    </div>
                    
                    <div className="livros-table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Disponível</th>
                                    <th>Exemplar</th>
                                    <th>ISBN</th>
                                    <th>Título</th>
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
    );
}

export default Livros;
