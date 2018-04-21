package com.parkway.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.parkway.core.dataobject.Dataset;
import com.parkway.core.dataobject.Param;
import com.parkway.core.dataobject.Record;
import com.parkway.core.dataobject.Result;

public class DataObjectUtil {
	
	public static void setParam(Result result, Param p){
		if(result != null && p != null){
			result.setParam(p);
		}
	}
	
	public static void setParam(Result result, String key, String value){
		setParam(result, key, value, "string");
	}
	
	public static void setParam(Result result, String key, String value, String type){
		if(result != null){
			Param p = result.findParam(key);
			if(p != null){
				p.setValue(value);
			}else{
				p = new Param(key, value, type);
				result.setParam(p);
			}
		}
	}
	
	public static String getStringParamValue(Record record, String key){
		return getStringParamValue(record, key, "");
	}
	
	public static String getStringParamValue(Record record, String key, String defaultValue){
		Param p = getParam(record, key);
		if(p != null && p.getValue() != null && !p.getValue().trim().isEmpty()){
			return p.getValue().trim();
		}else{
			return defaultValue;
		}
	}
	
	public static Param getParam(Record record, String key){
		if(record != null){
			return record.getParam(key);
		}
		else{
			return null;
		}
	}
	
	public static Param getParam(Result result, String key){
		if(result != null){
			return result.findParam(key);
		}
		else{
			return null;
		}
	}
	
	public static long getLongParamValue(Result result, String key){
		return getLongParamValue(result, key, null);
	}
	
	public static String getStringParamValue(Result result, String key){
		return getStringParamValue(result, key, "");
	}
	
	public static String getStringParamValue(Result result, String key, String defaultValue){
		Param p = getParam(result, key);

		if(p != null && p.getValue() != null){
			return p.getValue().trim();
		}else{
			return defaultValue;
		}
	}
	
	public static long getLongParamValue(Result result, String key, Long defaultValue){
		String value = getStringParamValue(result, key);

		//Default is 0
		long l = new Long(0);

		//Set Default value
		if(defaultValue != null){
			l = defaultValue;
		}

		if(!value.isEmpty()){
			try{
				// Unformat value
				value = NumberUtil.unformatAmount(value);
				l = Long.valueOf(value);
			}catch(Exception e){
				//Not a Long value
			}
		}

		return l;
	}
	
	public static int getIntParamValue(Result result, String key){
		return getIntParamValue(result, key, null);
	}
	
	public static int getIntParamValue(Result result, String key, Integer defaultValue){
		String value = getStringParamValue(result, key);

		//Default is 0
		int i = new Integer(0);

		//Set Default value
		if(defaultValue != null){
			i = defaultValue;
		}

		if(!value.isEmpty()){
			try{
				// Unformat value
				value = NumberUtil.unformatAmount(value);
				i = Integer.valueOf(value);
			}catch(Exception e){
				//Not a Int value
			}
		}

		return i;
	}
	
	public static boolean getBoolParamValue(Result result, String key){
		return getBoolParamValue(result, key, null);
	}
	
	public static boolean getBoolParamValue(Result result, String key, Boolean defaultValue){
		String value = getStringParamValue(result, key);

		//Default is false
		boolean b = false;

		//Set Default value
		if(defaultValue != null){
			b = defaultValue;
		}

		if(!value.isEmpty()){
			try{
				b = Boolean.valueOf(value);
			}catch(Exception e){
				//Not a Boolean value
			}
		}

		return b;
	}
	
	public static double getDoubleParamValue(Result result, String key){
		return getDoubleParamValue(result, key, null);
	}
	
	public static double getDoubleParamValue(Result result, String key, Double defaultValue){
		String value = getStringParamValue(result, key);

		//Default is 0
		double d = new Double(0.0);

		//Set Default value
		if(defaultValue != null){
			d = defaultValue;
		}

		if(!value.isEmpty()){
			try{
				// Unformat value
				value = NumberUtil.unformatAmount(value);
				d = Double.valueOf(value);
			}catch(Exception e){
				//Not a Double value
			}
		}

		return d;
	}
	
	public static Dataset createDataset(Result result, String id){
		Dataset d = getDataset(result, id);

		if(d == null){
			d = new Dataset(id);
			result.setDataSet(d);
		}

		return d;
	}
	
	public static Dataset createDataset(String id, ArrayList<Record> list){
		Dataset d = null;
		if(id != null){
			d = new Dataset(id);
			d.setRecords(list);
		}
		return d;
	}
	
	public static Dataset getDataset(Result result, String id){
		Dataset d = null;

		if(result != null && id != null && id.trim().length() > 0){

			ArrayList<Dataset> dList = result.getDataSets();

			if(dList != null){
				Iterator<Dataset> iter = dList.iterator();
				while (iter.hasNext()) {
					Dataset sdataset = iter.next();
					if (sdataset != null && id.equals(sdataset.getId()))
					{
						d = sdataset;
						break;
		}
				}
			}
		}

		return d;
	}
	
	public static Record getRecord(Result result, String id){
		Record r = null;

		if(result != null && id != null){
			
			List<Record> rList = result.getRecords();
			r = getRecordById(rList, id);
		}

		return r;
	}
	
	public static Record getRecordById(List<Record> records, String id) {

		Record r = null;
		
		if(records != null){
			Iterator<Record> iter = records.iterator();
			while (iter.hasNext()) {
				Record srecord = iter.next();
				if (srecord != null && id.equals(srecord.getId()))
				{
					r = srecord;
					break;
				}
			}
		}
		
		return r;
	}
	
	public static void setRecord(Result result, Record r, String id){
		if(r != null && result != null){
			r.setID(id);
			setRecord(result, r);
		}
	}
	
	public static void setRecord(Result result, Record r){

		if(result != null && r != null && r.getId() != null && !r.getId().trim().isEmpty()){

			Record record = getRecord(result, r.getId());
			if(record != null){
				//Remove old Record and Add new one
				ArrayList<Record> records = result.getRecords();
				records.remove(record);
			}

			//Add new record
			result.setRecord(r);
		}
	}
	
	public static void setDataset(Result result, Dataset d){

		if(result != null && d != null){
			
			Dataset d1 = null;
			String id = d.getId();
			
			if(id != null){
				d1 = getDataset(result, id);
			}
			
			// Remove old one with same name
			if(d1 != null){
				removeDataset(result, id);
			}
			
			result.setDataSet(d);
		}
	}
	
	public static void setDataset(Result result, ArrayList<Dataset> list){

		if (result != null && list != null) {
			for (Dataset dataset : list) {
				setDataset(result, dataset);
			}

		}
	}
	
	public static void setDataset(Result result, String id, ArrayList<Record> records){

		if(result != null && id != null && !id.trim().isEmpty() && records != null){
			Dataset d = new Dataset(id);
			d.setRecords(records);

			Dataset d1 = null;
			
			if(id != null){
				d1 = getDataset(result, id);
			}
			
			// Remove old one with same name
			if(d1 != null){
				removeDataset(result, id);
			}
			
			result.setDataSet(d);
		}
	}
	
	public static void removeParam(Result result, String id){
		if(result != null && id != null){
			Param p = result.findParam(id);
			if(p != null){
				result.getParamList().remove(p);
			}
		}
	}
	
	public static void removeParam(Record record, String id){
		if(record != null && id != null){
			Param p = record.getParam(id);
			if(p != null){
				record.getParams().remove(p);
			}
		}
	}
	
	public static void removeRecord(Result result, String id){
		if(result != null && id != null){
			Record r = result.getRecordById(id);
			if(r != null){
				result.getRecords().remove(r);
			}
		}
	}
	
	public static void removeRecord(Record record, String id){
		if(record != null && id != null){
			Record r = record.getRecordById(id);
			if(r != null){
				record.getRecords().remove(r);
			}
		}
	}
	
	public static void removeDataset(Result result, String id){
		if(result != null && id != null){
			Dataset d = result.findDataset(id);
			if(d != null){
				result.getDataSets().remove(d);
			}
		}
	}
	
	public static void removeDataset(Record record, String id){
		if(record != null && id != null){
			Dataset d = record.getDatasetById(id);
			if(d != null){
				record.getDatasets().remove(d);
			}
		}
	}
	
	public static void setParam(Record record, String key, String value){
		setParam(record, key, value, "string");
	}
	
	public static void setParam(Record record, String key, String value, String type){
		if(record != null && key != null && !key.trim().isEmpty()){
			Param p = record.getParam(key);

			if(value == null){
				value = "";
			}
			if(p != null){
				p.setValue(value);
			} else{
				p = new Param(key, value, type);
				record.setParam(p);
			}
		}
	}
	
	public static void setRecord(Dataset dataset, Record r){
		if(dataset != null && r != null){
			dataset.setRecord(r);
		}
	}
	
	public static void setDataset(Record record, Dataset d){

		if(record != null && d != null){
			
			Dataset d1 = null;
			String id = d.getId();
			
			if(id != null){
				d1 = getDataset(record, id);
			}
			
			// Remove old one with same name
			if(d1 != null){
				removeDataset(record, id);
			}
			
			record.setDataset(d);
		}
	}
	
	public static Dataset getDataset(Record record, String id){
		Dataset d = null;

		if(record != null && id != null && id.trim().length() > 0){

			ArrayList<Dataset> dList = record.getDatasets();

			if(dList != null){
				Iterator<Dataset> iter = dList.iterator();
				while (iter.hasNext()) {
					Dataset sdataset = iter.next();
					if (sdataset != null && id.equals(sdataset.getId()))
					{
						d = sdataset;
						break;
		}
				}
			}
		}

		return d;
	}

}
