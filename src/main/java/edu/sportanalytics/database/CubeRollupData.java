package edu.sportanalytics.database;

import java.util.ArrayList;
import java.util.List;

public class CubeRollupData
{
    private List<String> dim1;
    private List<String> dim2;
    private List<Double> aggie;

    public List<String> getDim1() {
        return dim1;
    }

    public List<String> getDim2() {
        return dim2;
    }

    public List<Double> getAggie() {
        return aggie;
    }

    public CubeRollupData()
    {
        dim1 = new ArrayList<>();
        dim2 = new ArrayList<>();
        aggie = new ArrayList<>();
    }

    public void appendDim1(String data)
    {
        dim1.add(data);
    }

    public void appendDim2(String data)
    {
        dim2.add(data);
    }

    public void appendAggie(double data)
    {
        aggie.add(data);
    }
}
