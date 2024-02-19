import React, { useEffect, useMemo, useState } from 'react';
import '../styles/pages/alunos.css';
import BackgroundLeft from '../components/background-left';
import { Aluno } from '../@types/types';
import api from '../util/axios';

function Alunos() {
    const [newStudent, setNewStudent] = useState<Aluno>({} as Aluno);
    const [alunos, setAlunos] = useState<Aluno[]>([]);
    const [search, setSearch] = useState<string>("");
    const [doSearch, setDoSearch] = useState<boolean>(false);
    const [updateTable, setUpdateTable] = useState<boolean>(false);

    const filteredStudents = useMemo(() => {
        return alunos?.filter((aluno) => {
          return aluno.nome.toLowerCase().includes(search.toLowerCase());
        });
      }, [alunos,doSearch]);

    const handleNewStudent = () => {
        console.log(newStudent);
        api.post('/aluno', {
            matricula: newStudent.matricula,
            nome: newStudent.nome,
            cpf: newStudent.cpf,
            endereco: newStudent.endereco
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
        setNewStudent({
            matricula: "",
            nome: "",
            cpf: "",
            endereco: ""
        });
    };

    useEffect(() => {
        api.get('/aluno')
            .then((response) => {
                setAlunos(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [updateTable]);

    return (
        <div className="alunos-background">
            <BackgroundLeft page="Alunos" />
            <div className="alunos-background-right">
                <div className="alunos-content-up">
                    <div className="alunos-title-container">
                        <h1>Cadastro de Alunos</h1>
                    </div>
                    <div className="alunos-content-container">
                        <div className="alunos-inputs-container">
                            <div className="alunos-input-container">
                                <label className="alunos-label">Matrícula</label>
                                <input  type="text" 
                                        placeholder="Digite a Matrícula" 
                                        className="alunos-input" 
                                        value={newStudent.matricula}
                                        onChange={(e: React.FormEvent) => {setNewStudent({...newStudent, matricula: (e.target as HTMLInputElement).value})}}
                                />
                            </div>
                            <div className="alunos-input-container">
                                <label className="alunos-label">Nome</label>
                                <input  type="text" 
                                        placeholder="Digite o Nome" 
                                        className="alunos-input" 
                                        value={newStudent.nome}
                                        onChange={(e: React.FormEvent) => {setNewStudent({...newStudent, nome: (e.target as HTMLInputElement).value})}}
                                />
                            </div>
                            <div className="alunos-input-container">
                                <label className="alunos-label">CPF</label>
                                <input  type="text" 
                                        placeholder="Digite o CPF"  
                                        className="alunos-input" 
                                        value={newStudent.cpf}
                                        onChange={(e: React.FormEvent) => {setNewStudent({...newStudent, cpf: (e.target as HTMLInputElement).value})}}
                                />
                            </div>
                            <div className="alunos-input-container">
                                <label className="alunos-label">Endereço</label>
                                <input  type="text" 
                                        placeholder="Digite o Endereço" 
                                        className="alunos-input" 
                                        value={newStudent.endereco}
                                        onChange={(e: React.FormEvent) => {setNewStudent({...newStudent, endereco: (e.target as HTMLInputElement).value})}}
                                />
                            </div>
                        </div>
                        <div className="alunos-options-container">
                            <button className="alunos-button" onClick={handleClear}>Limpar</button>
                            <button className="alunos-button" onClick={handleNewStudent}>Cadastrar</button>
                        </div>
                    </div>    
                </div>
                <div className="alunos-content-bottom">
                    <div className="alunos-title-container">
                        <h1>Listagem de Alunos</h1>
                    </div>
                    <div className="alunos-input-search-container">
                        <input  type="text" 
                                placeholder="Pesquisar" 
                                className="alunos-input-search" 
                                value={search}
                                onChange={(e: React.FormEvent) => setSearch((e.target as HTMLInputElement).value)}
                        />
                        <button className="alunos-button-search"
                                onClick={() => setDoSearch(!doSearch)}>
                                    Pesquisar
                        </button>
                    </div>
                    
                    <div className="alunos-table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Matrícula</th>
                                    <th>Nome</th>
                                    <th>CPF</th>
                                    <th>Endereço</th>
                                </tr>
                            </thead>
                            <tbody>
                            {(filteredStudents?.length || 0) > 0 ? 
                                filteredStudents.map((aluno) => (
                                    <tr key={aluno.matricula}>
                                        <td>{aluno.matricula}</td>
                                        <td>{aluno.nome}</td>
                                        <td>{aluno.cpf}</td>
                                        <td>{aluno.endereco}</td>
                                    </tr>
                                )) :
                                <tr>
                                    <td colSpan={4}>Nenhum aluno encontrado</td>
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

export default Alunos;
