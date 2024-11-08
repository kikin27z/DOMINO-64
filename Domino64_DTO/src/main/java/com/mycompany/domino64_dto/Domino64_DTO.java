/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.domino64_dto;

import entidadesDTO.FichaDTO;
import entidadesDTO.JugadaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class Domino64_DTO {

    public static void main(String[] args) {
        List<FichaDTO> fichas = new ArrayList<>();
        fichas.add(new FichaDTO(6, 6));
        fichas.add(new FichaDTO(4, 6));
        fichas.add(new FichaDTO(4, 1));
        fichas.add(new FichaDTO(0, 1));
        fichas.add(new FichaDTO(3, 0));
        fichas.add(new FichaDTO(3, 3));
        fichas.add(new FichaDTO(4, 3));
        JugadaDTO jugada = new JugadaDTO(4, 3);
        
        for(FichaDTO ficha : fichas){
            jugada.determinarJugada(ficha);
        }
    }
}
