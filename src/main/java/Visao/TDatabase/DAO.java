/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visao.TDatabase;

import Visao.TModels.Aluno;
import Visao.TModels.Livro;
import Visao.TModels.Materia;
import Visao.TModels.Nota;
import Visao.TModels.Pagamento;
import Visao.TModels.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sato
 */
public class DAO {

    public Aluno getAlunoByEmailAndSenha(String email, String senha) {
        Aluno aluno = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();

            if (connection != null) {
                String query = "SELECT * FROM alunos WHERE email = ? AND senha = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, senha);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    aluno = new Aluno(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("data_nascimento"),
                            resultSet.getString("sexo"),
                            resultSet.getString("endereco"),
                            resultSet.getString("cep"),
                            resultSet.getString("uf"),
                            resultSet.getString("email"),
                            resultSet.getString("telefone"),
                            resultSet.getString("rg"),
                            resultSet.getString("cpf"),
                            resultSet.getString("senha"),
                            resultSet.getString("numero_matricula")
                    );
                }
            } else {
                System.out.println("Erro ao obter conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return aluno;
    }

    public Professor getProfessorByEmailAndSenha(String email, String senha) {
        Professor professor = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();

            if (connection != null) {
                String query = "SELECT * FROM professores WHERE email = ? AND senha = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, senha);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    professor = new Professor(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("data_nascimento"),
                            resultSet.getString("sexo"),
                            resultSet.getString("endereco"),
                            resultSet.getString("cep"),
                            resultSet.getString("uf"),
                            resultSet.getString("email"),
                            resultSet.getString("telefone"),
                            resultSet.getString("rg"),
                            resultSet.getString("cpf"),
                            resultSet.getString("senha"),
                            resultSet.getString("registro")
                    );
                }
            } else {
                System.out.println("Erro ao obter conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return professor;
    }

    public ArrayList<Materia> getMateriasByAlunoId(int alunoId) {
        ArrayList<Materia> materias = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT m.id, m.nome FROM materias m "
                    + "JOIN alunos_materias am ON m.id = am.id_materia "
                    + "WHERE am.id_aluno = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Materia materia = new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nome")
                );
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materias;
    }

    public Nota getNotaByAlunoIdMateriaIdAndSemestre(int alunoId, int materiaId, int semestre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Nota nota = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT * FROM notas WHERE id_aluno = ? AND id_materia = ? AND semestre = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            preparedStatement.setInt(2, materiaId);
            preparedStatement.setInt(3, semestre);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nota = new Nota();
                nota.setId(resultSet.getInt("id"));
                nota.setIdAluno(resultSet.getInt("id_aluno"));
                nota.setIdMateria(resultSet.getInt("id_materia"));
                nota.setSemestre(resultSet.getInt("semestre"));
                nota.setNota(resultSet.getDouble("nota"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nota;
    }

    public Materia getMateriaByNome(String nome) {
        Materia materia = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT id, nome, id_professor FROM materias WHERE nome = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                materia = new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("id_professor")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materia;
    }

    public ArrayList<String> getAulasByMateriaId(int materiaId) {
        ArrayList<String> aulas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT data_aula, hora_inicio, hora_fim FROM calendario_aulas WHERE id_materia = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, materiaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String aula = "Data: " + resultSet.getString("data_aula") + ", Início: " + resultSet.getString("hora_inicio") + ", Fim: " + resultSet.getString("hora_fim");
                aulas.add(aula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return aulas;
    }

    public ArrayList<Pagamento> getPagamentosByAlunoId(int alunoId) {
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT data_pagamento, valor, status FROM mensalidades WHERE id_aluno = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pagamento pagamento = new Pagamento(
                        resultSet.getDate("data_pagamento"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("status")
                );
                pagamentos.add(pagamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pagamentos;
    }

    public void atualizarStatusPagamento(int pagamentoId, String novoStatus) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "UPDATE mensalidades SET status = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, novoStatus);
            preparedStatement.setInt(2, pagamentoId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean atualizarDadosAluno(int alunoId, String novoNome, String novoEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = false;

        try {
            connection = DBConnection.obtemConexao();
            String query = "UPDATE alunos SET nome = ?, email = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoEmail);
            preparedStatement.setInt(3, alunoId);

            int linhasAfetadas = preparedStatement.executeUpdate();
            sucesso = (linhasAfetadas > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return sucesso;
    }

    public ArrayList<Aluno> getAllAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT * FROM alunos";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("sexo"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("uf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("rg"),
                        resultSet.getString("cpf"),
                        resultSet.getString("senha"),
                        resultSet.getString("numero_matricula")
                );
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return alunos;
    }

    public ArrayList<Materia> getAllMaterias() {
        ArrayList<Materia> materias = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT * FROM materias";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Materia materia = new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("id_professor")
                );
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materias;
    }

    public void cadastrarAlunoEmMateria(int alunoId, int materiaId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "INSERT INTO alunos_materias (id_aluno, id_materia) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            preparedStatement.setInt(2, materiaId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrarLivro(String nome, String autor, String ano, String link, int materiaId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "INSERT INTO livros (nome, autor, ano, link, id_materia) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, autor);
            preparedStatement.setString(3, ano);
            preparedStatement.setString(4, link);
            preparedStatement.setInt(5, materiaId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Aluno> getAlunosByMateriaId(int materiaId) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT a.* FROM alunos a "
                    + "JOIN alunos_materias am ON a.id = am.id_aluno "
                    + "WHERE am.id_materia = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, materiaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("sexo"),
                        resultSet.getString("endereco"),
                        resultSet.getString("cep"),
                        resultSet.getString("uf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("rg"),
                        resultSet.getString("cpf"),
                        resultSet.getString("senha"),
                        resultSet.getString("numero_matricula")
                );
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return alunos;
    }

    public void lancarNota(Nota nota) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "INSERT INTO notas (id_aluno, id_materia, semestre, nota) VALUES (?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE nota = VALUES(nota)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nota.getIdAluno());
            preparedStatement.setInt(2, nota.getIdMateria());
            preparedStatement.setInt(3, nota.getSemestre());
            preparedStatement.setDouble(4, nota.getNota());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrarMateria(String nomeMateria, Date dataAula, String inicioAula, String fimAula, int professorId) {
        Connection connection = null;
        PreparedStatement preparedStatementMateria = null;
        PreparedStatement preparedStatementCalendario = null;

        try {
            connection = DBConnection.obtemConexao();
            connection.setAutoCommit(false); // Inicia a transação

            // Inserir na tabela de materias
            String queryMateria = "INSERT INTO materias (nome, id_professor) VALUES (?, ?)";
            preparedStatementMateria = connection.prepareStatement(queryMateria, Statement.RETURN_GENERATED_KEYS);
            preparedStatementMateria.setString(1, nomeMateria);
            preparedStatementMateria.setInt(2, professorId);
            preparedStatementMateria.executeUpdate();

            // Obter o ID gerado para a nova matéria
            ResultSet generatedKeys = preparedStatementMateria.getGeneratedKeys();
            int materiaId = 0;
            if (generatedKeys.next()) {
                materiaId = generatedKeys.getInt(1);
            }

            // Inserir na tabela de calendario_aulas
            String queryCalendario = "INSERT INTO calendario_aulas (id_materia, data_aula, hora_inicio, hora_fim) VALUES (?, ?, ?, ?)";
            preparedStatementCalendario = connection.prepareStatement(queryCalendario);
            preparedStatementCalendario.setInt(1, materiaId);
            preparedStatementCalendario.setDate(2, new java.sql.Date(dataAula.getTime()));
            preparedStatementCalendario.setString(3, inicioAula);
            preparedStatementCalendario.setString(4, fimAula);
            preparedStatementCalendario.executeUpdate();

            connection.commit(); // Confirma a transação

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Desfaz a transação em caso de erro
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatementMateria != null) {
                    preparedStatementMateria.close();
                }
                if (preparedStatementCalendario != null) {
                    preparedStatementCalendario.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Livro> getLivrosByAlunoId(int alunoId) {
        ArrayList<Livro> livros = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT l.id, l.titulo, l.autor, l.ano_publicacao, l.url "
                    + "FROM livros l "
                    + "JOIN materias_livros ml ON l.id = ml.id_livro "
                    + "JOIN materias m ON ml.id_materia = m.id "
                    + "JOIN alunos_materias am ON m.id = am.id_materia "
                    + "WHERE am.id_aluno = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("ano_publicacao"),
                        resultSet.getString("url")
                );
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return livros;
    }

    // Método para cadastrar uma matéria e seus horários de aula
    public void cadastrarMateriaAula(String nomeMateria, Date dataAula, String inicioAula, String fimAula, int idProfessor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = DBConnection.obtemConexao();

            // Passo 1: Cadastrar a matéria
            String queryMateria = "INSERT INTO materias (nome, id_professor) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(queryMateria, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nomeMateria);
            preparedStatement.setInt(2, idProfessor);
            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            int idMateria = 0;
            if (generatedKeys.next()) {
                idMateria = generatedKeys.getInt(1);
            }

            // Passo 2: Cadastrar os horários das aulas
            String queryAula = "INSERT INTO calendario_aulas (id_materia, data_aula, hora_inicio, hora_fim) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryAula);
            preparedStatement.setInt(1, idMateria);
            preparedStatement.setDate(2, new java.sql.Date(dataAula.getTime()));
            preparedStatement.setString(3, inicioAula);
            preparedStatement.setString(4, fimAula);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Materia> getMateriasByProfessorId(int professorId) {
        ArrayList<Materia> materias = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT * FROM materias WHERE id_professor = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Materia materia = new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("id_professor")
                );
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materias;
    }

    public ArrayList<Livro> getLivrosByMateriaId(int materiaId) {
        ArrayList<Livro> livros = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT l.id, l.titulo, l.autor, l.ano_publicacao, l.url "
                    + "FROM livros l "
                    + "JOIN materias_livros ml ON l.id = ml.id_livro "
                    + "WHERE ml.id_materia = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, materiaId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("ano_publicacao"),
                        resultSet.getString("url")
                );
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return livros;
    }

    public void cadastrarLivro(String titulo, String autor, int anoPublicacao, String url, int materiaId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            String queryLivro = "INSERT INTO livros (titulo, autor, ano_publicacao, url) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryLivro, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, autor);
            preparedStatement.setInt(3, anoPublicacao);
            preparedStatement.setString(4, url);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int livroId = 0;
            if (generatedKeys.next()) {
                livroId = generatedKeys.getInt(1);
            }

            String queryMateriaLivro = "INSERT INTO materias_livros (id_materia, id_livro) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(queryMateriaLivro);
            preparedStatement.setInt(1, materiaId);
            preparedStatement.setInt(2, livroId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Nota getNotaByAlunoIdMateriaIdSemestre(int alunoId, int materiaId, int semestre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Nota nota = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT * FROM notas WHERE id_aluno = ? AND id_materia = ? AND semestre = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            preparedStatement.setInt(2, materiaId);
            preparedStatement.setInt(3, semestre);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nota = new Nota();
                nota.setId(resultSet.getInt("id"));
                nota.setIdAluno(resultSet.getInt("id_aluno"));
                nota.setIdMateria(resultSet.getInt("id_materia"));
                nota.setSemestre(resultSet.getInt("semestre"));
                nota.setNota(resultSet.getDouble("nota"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nota;
    }

    public List<Livro> getLivrosByProfessorId(int professorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Livro> livros = new ArrayList<>();

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT l.id, l.titulo, l.autor, l.ano_publicacao, l.url "
                    + "FROM livros l "
                    + "JOIN materias_livros ml ON l.id = ml.id_livro "
                    + "JOIN materias m ON ml.id_materia = m.id "
                    + "WHERE m.id_professor = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Livro livro = new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("autor"),
                        resultSet.getInt("ano_publicacao"),
                        resultSet.getString("url")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return livros;
    }

    public void cadastrarLivro(Livro livro, int idMateria) throws SQLException {
        String queryLivro = "INSERT INTO livros (titulo, autor, ano_publicacao, url) VALUES (?, ?, ?, ?)";
        String queryMateriaLivro = "INSERT INTO materias_livros (id_materia, id_livro) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatementLivro = null;
        PreparedStatement preparedStatementMateriaLivro = null;
        ResultSet generatedKeys = null;

        try {
            connection = DBConnection.obtemConexao();
            connection.setAutoCommit(false);

            preparedStatementLivro = connection.prepareStatement(queryLivro, Statement.RETURN_GENERATED_KEYS);
            preparedStatementLivro.setString(1, livro.getTitulo());
            preparedStatementLivro.setString(2, livro.getAutor());
            preparedStatementLivro.setInt(3, livro.getAnoPublicacao());
            preparedStatementLivro.setString(4, livro.getUrl());
            preparedStatementLivro.executeUpdate();

            generatedKeys = preparedStatementLivro.getGeneratedKeys();
            if (generatedKeys.next()) {
                int livroId = generatedKeys.getInt(1);

                preparedStatementMateriaLivro = connection.prepareStatement(queryMateriaLivro);
                preparedStatementMateriaLivro.setInt(1, idMateria);
                preparedStatementMateriaLivro.setInt(2, livroId);
                preparedStatementMateriaLivro.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (preparedStatementLivro != null) {
                preparedStatementLivro.close();
            }
            if (preparedStatementMateriaLivro != null) {
                preparedStatementMateriaLivro.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removerLivro(int livroId) throws SQLException {
        String deleteMateriasLivrosQuery = "DELETE FROM materias_livros WHERE id_livro = ?";
        String deleteLivroQuery = "DELETE FROM livros WHERE id = ?";

        Connection connection = null;
        PreparedStatement deleteMateriasLivrosStmt = null;
        PreparedStatement deleteLivroStmt = null;

        try {
            connection = DBConnection.obtemConexao();
            connection.setAutoCommit(false);

            // Excluir da tabela materias_livros primeiro
            deleteMateriasLivrosStmt = connection.prepareStatement(deleteMateriasLivrosQuery);
            deleteMateriasLivrosStmt.setInt(1, livroId);
            deleteMateriasLivrosStmt.executeUpdate();

            // Excluir da tabela livros
            deleteLivroStmt = connection.prepareStatement(deleteLivroQuery);
            deleteLivroStmt.setInt(1, livroId);
            deleteLivroStmt.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (deleteMateriasLivrosStmt != null) {
                deleteMateriasLivrosStmt.close();
            }
            if (deleteLivroStmt != null) {
                deleteLivroStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int getLivroIdByName(String titulo) {
        String query = "SELECT id FROM livros WHERE titulo = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int livroId = -1;

        try {
            connection = DBConnection.obtemConexao();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, titulo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                livroId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return livroId;
    }

    public int getMateriaIdByName(String nome) {
        String query = "SELECT id FROM materias WHERE nome = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int materiaId = -1;

        try {
            connection = DBConnection.obtemConexao();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                materiaId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materiaId;
    }

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        String query = "INSERT INTO alunos (nome, data_nascimento, sexo, endereco, cep, uf, email, telefone, rg, cpf, senha, numero_matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getDataNascimento());
            preparedStatement.setString(3, aluno.getSexo());
            preparedStatement.setString(4, aluno.getEndereco());
            preparedStatement.setString(5, aluno.getCep());
            preparedStatement.setString(6, aluno.getUf());
            preparedStatement.setString(7, aluno.getEmail());
            preparedStatement.setString(8, aluno.getTelefone());
            preparedStatement.setString(9, aluno.getRg());
            preparedStatement.setString(10, aluno.getCpf());
            preparedStatement.setString(11, aluno.getSenha());
            preparedStatement.setString(12, aluno.getNumeroMatricula());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void cadastrarProfessor(Professor professor) throws SQLException {
        String query = "INSERT INTO professores (nome, data_nascimento, sexo, endereco, cep, uf, email, telefone, rg, cpf, senha, registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.obtemConexao();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setString(2, professor.getDataNascimento());
            preparedStatement.setString(3, professor.getSexo());
            preparedStatement.setString(4, professor.getEndereco());
            preparedStatement.setString(5, professor.getCep());
            preparedStatement.setString(6, professor.getUf());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getTelefone());
            preparedStatement.setString(9, professor.getRg());
            preparedStatement.setString(10, professor.getCpf());
            preparedStatement.setString(11, professor.getSenha());
            preparedStatement.setString(12, professor.getRegistro());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
