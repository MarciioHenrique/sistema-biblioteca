export interface Aluno {
    matricula: string;
    nome: string;
    cpf: string;
    endereco: string;
}

export interface LivroRequest {
    disponivel: boolean;
    exemplarBiblioteca: boolean;
    titulo: Titulo;
}

export interface LivroResponse {
    id: number;
    disponivel: boolean;
    exemplarBiblioteca: boolean;
    titulo: Titulo;
}

export interface Titulo {
    id: number;
    prazo: number;
    isbn: string;
    titulo: string;
}

export interface EmprestimoRequest {
    alunoMatricula: string;
    itensEmprestimo: LivroResponse[];
    dataEmprestimo: string;
}

export interface DevolucaoRequest {
    dataDevolucao: string;
    alunoMatricula: string;
    itensDevolucao: LivroResponse[];
}