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
import java.util.ArrayList;

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

    public Nota getNotasByAlunoIdAndMateriaId(int alunoId, int materiaId) {
        Nota nota = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtemConexao();
            String query = "SELECT n.id, n.id_aluno, n.id_materia, n.semestre, n.nota "
                    + "FROM notas n "
                    + "WHERE n.id_aluno = ? AND n.id_materia = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alunoId);
            preparedStatement.setInt(2, materiaId);
            resultSet = preparedStatement.executeQuery();

            ArrayList<Double> notas = new ArrayList<>();
            while (resultSet.next()) {
                notas.add(resultSet.getDouble("nota"));
            }

            if (!notas.isEmpty()) {
                nota = new Nota();
                nota.setIdAluno(alunoId);
                nota.setIdMateria(materiaId);
                nota.setNota1(notas.size() > 0 ? notas.get(0) : 0.0);
                nota.setNota2(notas.size() > 1 ? notas.get(1) : 0.0);
                nota.setNota3(notas.size() > 2 ? notas.get(2) : 0.0);
                nota.setNota4(notas.size() > 3 ? notas.get(3) : 0.0);
                nota.calcularMedia();
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
}
