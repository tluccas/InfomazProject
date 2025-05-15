package org.example.service;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

public interface RankingService <T> {

    public void CalcularRanking() throws SQLException;

    public List<T> getRanking();

    public void exibirRanking();

}
