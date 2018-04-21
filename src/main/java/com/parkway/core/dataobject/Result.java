package com.parkway.core.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Result implements Serializable
{
  private ArrayList<Param> params = new ArrayList<Param>();
  private ArrayList<Dataset> datasets = new ArrayList<Dataset>();
  private ArrayList<Record> records = new ArrayList<Record>();

  public ArrayList<Param> getParamList()
  {
    return this.params;
  }

  public void setParamList(ArrayList<Param> params)
  {
    this.params = params;
  }

  public void setParam(Param param)
  {
    this.params.add(param);
  }

  public ArrayList<Dataset> getDataSets()
  {
    return this.datasets;
  }

  public void setDataSets(ArrayList<Dataset> datasets)
  {
    this.datasets = datasets;
  }

  public void setDataSet(Dataset dataset)
  {
    this.datasets.add(dataset);
  }

  public void removeParam(Param param)
  {
    if (this.params.contains(param))
      this.params.remove(param);
  }

  public Param findParam(String id)
  {
    Param param = null;
    Iterator itr;
    if ((id != null) && (id.trim().length() > 0)) {
      for (itr = this.params.iterator(); itr.hasNext(); ) {
        Param sparam = (Param)itr.next();
        if (id.equals(sparam.getName())) {
          param = sparam;
          break;
        }
      }
    }
    return param;
  }

  public Dataset findDataset(String id)
  {
    Dataset dataset = null;
    Iterator itr;
    if ((id != null) && (id.trim().length() > 0)) {
      for (itr = this.datasets.iterator(); itr.hasNext(); ) {
        Dataset sdataset = (Dataset)itr.next();
        if (id.equals(sdataset.getId())) {
          dataset = sdataset;
          break;
        }
      }
    }
    return dataset;
  }

  public Record getRecordById(String id)
  {
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

  public ArrayList<Record> getRecords()
  {
    if (this.records == null) {
      this.records = new ArrayList<Record>();
    }
    return this.records;
  }

  public void setRecords(ArrayList<Record> records)
  {
    this.records = records;
  }

  public void setRecord(Record record)
  {
    if (this.records == null) {
      this.records = new ArrayList<Record>();
    }
    this.records.add(record);
  }
}
