package edu.sportanalytics.database;

public enum AggregationEnum
{
    AVG
            {
                @Override
                public String toString() {
                    return "AVG";
                }
            },
    MIN
            {
                @Override
                public String toString() {
                    return "MIN";
                }
            },
    MAX
            {
                @Override
                public String toString() {
                    return "MAX";
                }
            },
    SUM
            {
                @Override
                public String toString() {
                    return "SUM";
                }
            },
    UNKNOWN;
}
