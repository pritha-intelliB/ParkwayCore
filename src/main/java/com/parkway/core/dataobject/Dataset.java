package com.parkway.core.dataobject;

import java.io.Serializable;
import java.util.ArrayList;

public class Dataset implements Serializable
{
  private String id = "";
  public int index = -1;
  private ArrayList<Record> records = new ArrayList<Record>();

  public Dataset()
  {
  }

  public Dataset(String id) {
    this.id = id;
  }

  public ArrayList<Record> getRecords() {
    return this.records;
  }

  public void setRecord(Record rec) {
    this.records.add(rec);
  }

  public void setRecords(ArrayList<Record> records) {
    this.records = records;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Record getRecord(int index) {
    Record record = null;
    if (index <= getRecords().size() - 1) {
      record = (Record)getRecords().get(index);
    }
    return record;
  }

  public Record getRecordOrder(int order)
  {
    Record record = null;
    int size = getRecords().size();
    for (int idx = 0; idx < size; idx++) {
      Record rec = (Record)getRecords().get(idx);
      if (order == rec.order) {
        record = rec;
        break;
      }
    }
    return record;
  }
}
