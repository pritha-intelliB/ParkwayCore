package com.parkway.core.dataobject;

import java.io.Serializable;
import java.util.ArrayList;

public class Record  implements Comparable<Record>, Serializable
{
  public int index = -1;
  public int order = 0;
  private String id = "";
  private ArrayList<Param> params = new ArrayList<Param>();
  private ArrayList<Dataset> datasets = new ArrayList<Dataset>();
  private ArrayList<Record> records = new ArrayList<Record>();

  public ArrayList<Param> getParams() {
    return this.params;
  }

  public void setParam(Param param) {
    this.params.add(param);
  }

  public void setParams(ArrayList<Param> params) {
    this.params = params;
  }

  public ArrayList<Dataset> getDatasets() {
    return this.datasets;
  }

  public void setDataset(Dataset dataset) {
    this.datasets.add(dataset);
  }

  public void setDatasets(ArrayList<Dataset> datasets) {
    this.datasets = datasets;
  }

  public ArrayList<Record> getRecords() {
    if (this.records == null)
      this.records = new ArrayList<Record>();
    return this.records;
  }

  public void setRecords(ArrayList<Record> records) {
    this.records = records;
  }

  public int compareTo(Record o) {
    return this.order - o.order;
  }

  public Param getParam(String name) {
    Param param = null;
    int size = this.params.size();
    for (int idx = 0; idx < size; idx++) {
      Param tmp = (Param)getParams().get(idx);
      if (tmp.getName().equals(name)) {
        param = tmp;
        break;
      }
    }
    return param;
  }

  public Dataset getDatasetById(String id) {
    Dataset dataset = null;
    for (int idx = 0; idx < this.datasets.size(); idx++) {
      Dataset tmp = (Dataset)getDatasets().get(idx);
      if (tmp.getId().equals(id)) {
        dataset = tmp;
        break;
      }
    }
    return dataset;
  }

  public Record getRecordById(String id) {
    Record record = null;
    for (int idx = 0; idx < this.records.size(); idx++) {
      Record rec = (Record)getRecords().get(idx);
      if (rec.getId().equals(id)) {
        record = rec;
        break;
      }
    }
    return record;
  }

  public String getId() {
    if (this.id == null)
      this.id = "";
    return this.id;
  }

  public void setID(String id) {
    this.id = id;
  }
}
