package repository;

import entities.Cargo;

import java.util.List;

public interface ICargo {
    Cargo guardar(Cargo cargo);
    List<Cargo> listar();
}