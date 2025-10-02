/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author AnnaLauraBalmizaSoar
 */
public class Controlador {

    @FXML
    private TextField nome;
    @FXML
    private TextField cep;
    @FXML
    private TextField rua;
    @FXML
    private TextField cidade;
    @FXML
    private TextField numero;
    @FXML
    private TextField estado;
    @FXML
    private TextField telefone;
    private ArrayList<Cliente> listaClientes;

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, Integer> colunaCodigo;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaCidade;
    @FXML
    private TableColumn<Cliente, String> colunaEstado;
    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    private void initialize() {
        Buscador buscador = new Buscador();
        listaClientes = new ArrayList();
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaCidade.setCellValueFactory(cid -> {
            return new SimpleStringProperty(cid.getValue().getEndereco().getCidade());
        });
        colunaEstado.setCellValueFactory(cid -> {
            return new SimpleStringProperty(cid.getValue().getEndereco().getEstado());
        });
    }

    @FXML
    private void cadastrarCliente() {

        Cliente cliente = new Cliente();

        Endereco endereco = new Endereco();

        cliente.setNome(nome.getText());

        endereco.setRua(rua.getText());

        endereco.setCidade(cidade.getText());

        endereco.setEstado(estado.getText());

        endereco.setCep(cep.getText());
        
        endereco.setNumero(numero.getText());
        
        cliente.setEndereco(endereco);

        cliente.setTelefone(telefone.getText());

        

       

        listaClientes.add(cliente);
        tabelaClientes.setItems(FXCollections.observableArrayList(listaClientes));

        limparCampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "cadastrado com sucesso");

        alert.show();

    }

    @FXML

    private void limparCampos() {

        nome.setText("");

        cep.setText("");

        rua.setText("");

        numero.setText("");

        cidade.setText("");

        estado.setText("");

        telefone.setText("");

    }

    @FXML

    private void buscarCEP() throws IOException {

        String cepText = cep.getText();

        Buscador buscador = new Buscador();

        try {

            Endereco endereco = buscador.buscar(cepText);

            rua.setText(endereco.getRua());

            cidade.setText(endereco.getCidade());

            estado.setText(endereco.getEstado());

            numero.setText(endereco.getNumero());

        } catch (IllegalArgumentException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Formato de CEP inválido!");

            alert.show();

        } catch (IOException e) {

            System.out.println(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.show();

        }
    }
}
