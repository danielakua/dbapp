package com.example.danielakua.dbapp;

class Record
{
    private String _name;// keeps name of user
    private Double _score;// keeps the score of the user
    private int _id;// id for database

    public Record(){}

    public Record(int id, String name, Double score) {
        _name = name;
        _score = score;
        _id = id;
    }

    public Record(String name, Double score) {
        _name = name;
        _score = score;
    }

    public String get_name() { return _name; }

    public Double get_score() { return _score; }

    public int get_id() { return _id; }

    public void set_name(String name) { _name = name; }

    public void set_score(Double score) { _score = score; }

    public void set_id(int id) { _id = id; }
}
