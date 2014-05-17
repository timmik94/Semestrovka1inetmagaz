package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.Status;
import org.javalab.inetmagaz.model.Zakaz;

import java.util.List;

/**
 * Created by timur on 11.05.2014.
 */
public interface ZakazDao {
    public void setzakaz(Zakaz zakaz);
    public List<Zakaz> getzakaz(String login);
    public void updatestatus(int zakazid,Status status);
}
