package com.parkway.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parkway.core.dataobject.Dataset;
import com.parkway.core.dataobject.Param;
import com.parkway.core.dataobject.Record;
import com.parkway.core.dataobject.Result;
import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;


@SuppressWarnings({"unchecked", "unused", "rawtypes"})
public class JsonUtil
{
	private static final ELogger logger = ELoggerFactory.getLogger(JsonUtil.class);

	public static String convert(Dataset ds) throws JSONException{
		if(ds == null){
			return null;
		}

		return convertDataset(ds).toString();
	}

	public static String convert(Result result) throws JSONException
	{
		return convert(result, null);
	}
	
	public static String convert(Record record) throws JSONException
	{
		return debugConvertRecord(record).toString();
	}

	public static String convert(Result result, Object dresponse) throws JSONException
	{

		if(result != null){

			JSONObject jsonObject = new JSONObject();

			ArrayList<Param> paramList = result.getParamList();
			ArrayList<Dataset> dataSetList = result.getDataSets();
			ArrayList<Record> recordList = result.getRecords();

			boolean responseExits = dresponse != null;

			//Process Params
			if (paramList != null)
			{
				int sz = paramList.size();
				for (int i = 0; i < sz; i++)
				{
					Param param = paramList.get(i);
					populateJsn(param, jsonObject, false);
				}
			}

			//Process Records
			if (recordList != null)
			{
				int rsz = recordList.size();
				for (int i = 0; i < rsz; i++)
				{
					Record record = recordList.get(i);
					if(record != null){
						String id = record.getId();
						JSONObject jrec = convertRecord(record);

						if(id == null){
							id = "record" + i;
						}
						jsonObject.put(id, jrec);
					}
				}
			}

			//Process Datasets
			if (dataSetList != null)
			{
				int dsz = dataSetList.size();
				for (int i = 0; i < dsz; i++)
				{
					Dataset dataset = dataSetList.get(i);
					if(dataset != null){
						String id = dataset.getId();
						JSONArray jArrayRecords = convertDataset(dataset);

						if(id == null){
							id = "dataset" + i;
						}
						jsonObject.put(id, jArrayRecords);
					}
				}
			}

			return jsonObject.toString();
		}

		return "";
	}

	public static JSONArray convertDataset(Dataset dataset) throws JSONException
	{
		JSONArray jArrayRecords = new JSONArray();
		ArrayList<Record> ar = dataset.getRecords();
		int arsz = ar.size();
		for (int j = 0; j < arsz; j++)
		{
			//Id for Record inside Dataset will be ignored
			Record rc = ar.get(j);
			JSONObject jRec = new JSONObject();

			//Process Params
			ArrayList<Param> paramArray = rc.getParams();
			int paramsz = paramArray.size();
			for (int k = 0; k < paramsz; k++)
			{
				Param param = paramArray.get(k);
				populateJsn(param, jRec, false);
			}

			//Process Records
			ArrayList<Record> recordList = rc.getRecords();
			if (recordList != null)
			{
				int rsz = recordList.size();
				for (int i = 0; i < rsz; i++)
				{
					Record record = recordList.get(i);
					if(record != null){
						String id = record.getId();
						JSONObject jRecCh = convertRecord(record);

						if(id == null){
							id = "record" + i;
						}
						jRec.put(id, jRecCh);
					}
				}
			}

			//Process Datasets
			ArrayList<Dataset> dataSetList = rc.getDatasets();
			if (dataSetList != null)
			{
				int datasetsz = dataSetList.size();
				for (int k = 0; k < datasetsz; k++)
				{
					Dataset subDataset = dataSetList.get(k);
					if (subDataset != null)
					{
						String id = subDataset.getId();
						JSONArray jSubRecords = convertDataset(subDataset);

						if(id == null){
							id = "dataset" + k;
						}
						jRec.put(id, jSubRecords);
					}
				}
			}

			jArrayRecords.put(jRec);
		}
		return jArrayRecords;
	}

	public static JSONObject convertRecord(Record record) throws JSONException
	{
		JSONObject jRec = new JSONObject();

		//Process Params
		ArrayList<Param> paramArray = record.getParams();
		int paramsz = paramArray.size();
		for (int k = 0; k < paramsz; k++)
		{
			Param param = paramArray.get(k);
			populateJsn(param, jRec, false);
		}

		//Process Records
		ArrayList<Record> records = record.getRecords();
		if (records != null)
		{
			int rsz = records.size();
			for (int i = 0; i < rsz; i++)
			{
				Record innerRecord = records.get(i);
				if(innerRecord != null){
					String id = innerRecord.getId();
					JSONObject innerObject = convertRecord(innerRecord);

					if(id == null){
						id = "record" + i;
					}
					jRec.put(id, innerObject);
				}
			}
		}

		//Process Datasets
		ArrayList<Dataset> datasets = record.getDatasets();
		if (datasets != null)
		{
			int dsz = datasets.size();
			for (int i = 0; i < dsz; i++)
			{
				Dataset innerDataset = datasets.get(i);
				if(innerDataset != null){
					String id = innerDataset.getId();
					JSONArray innerObject = convertDataset(innerDataset);
					if(id == null){
						id = "dataset" + i;
					}
					jRec.put(id, innerObject);
				}
			}
		}

		return jRec;
	}

	private static JSONObject debugConvertRecord(Record record) throws JSONException
	{
		JSONObject jRec = new JSONObject();

		//Process Params
		ArrayList<Param> paramArray = record.getParams();
		int paramsz = paramArray.size();
		for (int k = 0; k < paramsz; k++)
		{
			Param param = paramArray.get(k);
			populateJsn(param, jRec, true);
		}

		//Process Records
		ArrayList<Record> records = record.getRecords();
		if (records != null)
		{
			int rsz = records.size();
			for (int i = 0; i < rsz; i++)
			{
				Record innerRecord = records.get(i);
				if(innerRecord != null){
					String id = innerRecord.getId();
					JSONObject innerObject = convertRecord(innerRecord);
					if(id == null){
						id = "record" + i;
					}
					jRec.put(id, innerObject);
				}
			}
		}

		//Process Datasets
		ArrayList<Dataset> datasets = record.getDatasets();
		if (datasets != null)
		{
			int dsz = datasets.size();
			for (int i = 0; i < dsz; i++)
			{
				Dataset innerDataset = datasets.get(i);
				if(innerDataset != null){
					String id = innerDataset.getId();
					JSONArray innerObject = convertDataset(innerDataset);
					if(id == null){
						id = "dataset" + i;
					}
					jRec.put(id, innerObject);
				}
			}
		}

		return jRec;
	}

	private static JSONArray debugConvertDataset(Dataset dataset) throws JSONException
	{
		JSONArray jArrayRecords = new JSONArray();

		ArrayList<Record> ar = dataset.getRecords();
		int arsz = ar.size();
		for (int j = 0; j < arsz; j++)
		{
			//Record Id inside Dataset will be ignored
			Record rc = ar.get(j);
			JSONObject jRec = new JSONObject();

			//Process Params
			ArrayList<Param> paramArray = rc.getParams();
			int paramsz = paramArray.size();
			for (int k = 0; k < paramsz; k++)
			{
				Param param = paramArray.get(k);
				populateJsn(param, jRec, true);
			}

			//Process Records
			ArrayList<Record> recordList = rc.getRecords();
			if (recordList != null)
			{
				int rsz = recordList.size();
				for (int i = 0; i < rsz; i++)
				{
					Record record = recordList.get(i);
					if(record != null){
						String id = record.getId();
						JSONObject jRecCh = debugConvertRecord(record);
						if(id == null){
							id = "record" + i;
						}
						jRec.put(id, jRecCh);
					}
				}
			}

			//Process Datasets
			ArrayList<Dataset> dataSetList = rc.getDatasets();
			if (dataSetList != null)
			{
				int datasetsz = dataSetList.size();
				for (int k = 0; k < datasetsz; k++)
				{
					Dataset subDataset = dataSetList.get(k);
					if (subDataset != null)
					{
						String id = subDataset.getId();
						JSONArray jSubRecords = debugConvertDataset(subDataset);
						if(id == null){
							id = "dataset" + k;
						}
						jRec.put(id, jSubRecords);
					}
				}
			}

			jArrayRecords.put(jRec);
		}
		return jArrayRecords;
	}

	public static String debugConvert(Result result) throws JSONException
	{

		if(result != null){
			
			JSONObject jsonObject = new JSONObject();
			
			ArrayList<Param> paramList = result.getParamList();
			ArrayList<Dataset> dataSetList = result.getDataSets();
			ArrayList<Record> recordList = result.getRecords();

			//Process Params
			if (paramList != null)
			{
				int sz = paramList.size();
				for (int i = 0; i < sz; i++)
				{
					Param param = paramList.get(i);
					populateJsn(param, jsonObject, true);
				}
			}

			//Process Records
			if (recordList != null)
			{
				int rsz = recordList.size();
				for (int i = 0; i < rsz; i++)
				{
					Record record = recordList.get(i);
					if(record != null){
						String id = record.getId();
						JSONObject jrec = debugConvertRecord(record);
						if(id == null){
							id = "record" + i;
						}
						jsonObject.put(id, jrec);
					}
				}
			}

			//Process Datasets
			if (dataSetList != null)
			{
				int dsz = dataSetList.size();
				for (int i = 0; i < dsz; i++)
				{
					Dataset dataset = dataSetList.get(i);
					if(dataset != null){
						String id = dataset.getId();
						JSONArray jArrayRecords = debugConvertDataset(dataset);
						if(id == null){
							id = "dataset" + i;
						}
						jsonObject.put(id, jArrayRecords);
					}
				}
			}

			return jsonObject.toString();
		}

		return "";
	}

	public static void populateJsn(Param param, JSONObject jsonObject, boolean mask) throws JSONException
	{
		if (param == null) {
			return;
		}
		if ("number".equals(param.getType()))
		{
			if ("Currency".equals(param.getFormat())) {
				addToJsonObject(jsonObject, param.getName(), parseCurrency(param), mask);
			} else if ("Number".equals(param.getFormat())) {
				addToJsonObject(jsonObject, param.getName(), parseNumber(param), mask);
			} else {
				try
				{
					addToJsonObject(jsonObject, param.getName(), NumberFormat.getNumberInstance().parse(param.getValue()), mask);
				}
				catch (ParseException e)
				{
					addToJsonObject(jsonObject, param.getName(), param.getValue(), mask);
				}
			}
		}
		else if ("int".equals(param.getType()))
		{
			addToJsonObject(jsonObject, param.getName(), Integer.valueOf(Integer.parseInt(param.getValue())), mask);
		}
		else if ("double".equals(param.getType()))
		{
			addToJsonObject(jsonObject, param.getName(), Float.valueOf(Float.parseFloat(param.getValue())), mask);
		}
		else if ("boolean".equals(param.getType()))
		{
			addToJsonObject(jsonObject, param.getName(), Boolean.valueOf(param.getValue()), mask);
		}
		else if ("Currency".equals(param.getFormat()))
		{
			addToJsonObject(jsonObject, param.getName(), parseCurrency(param), mask);
		}
		else if ("Number".equals(param.getFormat()))
		{
			addToJsonObject(jsonObject, param.getName(), parseNumber(param), mask);
		}
		else if (("Date".equals(param.getFormat())) || ((param.getObjectValue() != null) && ((param.getObjectValue() instanceof Date))))
		{
			try
			{
				addToJsonObject(jsonObject, param.getName(), parseDate(param), mask);
			}
			catch (ParseException e)
			{
				addToJsonObject(jsonObject, param.getName(), param.getValue(), mask);
			}
		}
		else
		{
			String str = param.getValue();

			addToJsonObject(jsonObject, param.getName(), param.getValue(), mask);
		}
	}

	private static String parseDate(Param param) throws ParseException
	{
		String st = null;
		Date date = null;

		String frmtValue = param.getFormatValue();
		if ((frmtValue != null) && (frmtValue.trim().length() > 0))
		{
			String[] tokens = frmtValue.trim().split("~");
			SimpleDateFormat sdf = new SimpleDateFormat(tokens[0]);
			if ((param.isObjectSet()) && (param.getObjectValue() != null)) {
				date = (Date)param.getObjectValue();
			} else {
				date = sdf.parse(param.getValue());
			}
			sdf = new SimpleDateFormat(tokens[1]);
			st = sdf.format(date);
		}
		else if ((param.isObjectSet()) && (param.getObjectValue() != null))
		{
			st = param.getObjectValue().toString();
		}
		return st;
	}

	private static String parseNumber(Param param)
	{
		float floatValue = Float.parseFloat(param.getValue());
		String str = NumberFormat.getInstance().format(floatValue);
		return str;
	}

	private static String parseCurrency(Param param)
	{
		float floatValue = Float.parseFloat(param.getValue());
		String str = NumberFormat.getCurrencyInstance().format(floatValue);
		return str;
	}

	private static void addToJsonObject(JSONObject jsonObject, String name, Object value, boolean mask) throws JSONException
	{
		jsonObject.put(name, value);
	}

	public static void main1(String[] args)
	{
		Result res = new Result();
		Param out = new Param("out", "Weâ€™re sorry", "string");
		res.setParam(out);
		out = new Param("opstatus", "0", "int");
		res.setParam(out);
		String str = null;
		try
		{
			str = convert(res);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		System.out.println("JSON String " + str);
	}

	/**
	 * createResult - Create Result from JSON String
	 * @param json
	 * @return
	 */
	public static Result createResult(String json){

		Result result = new Result();

		if(json != null){
			json = json.trim();

			if(json.startsWith("[")){
				try {
					JSONArray jArray = new JSONArray(json);
					Dataset d = createDataset(jArray);
					d.setId("dataset");
					result.setDataSet(d);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}else{
				try {
					JSONObject jObj = new JSONObject(json);
					result = createResult(jObj);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}
		}

		return result;
	}

	/**
	 * createResult - Create Result form JSON Object
	 * @param json
	 * @return
	 */
	public static Result createResult(JSONObject json){
		Result result = new Result();

		if(json != null){
			Iterator<String> iter = json.keys();
			while(iter.hasNext()){
				String key = iter.next();

				Object value = json.opt(key);

				if(value != null){
					if(value instanceof JSONObject){
						ArrayList<Record> records = result.getRecords();

						//Create Record
						Record r1 = createRecord((JSONObject) value);
						r1.setID(key);
						records.add(r1);

					}else if(value instanceof JSONArray){
						//Create Dataset
						Dataset d1 = createDataset((JSONArray) value);
						d1.setId(key);
						result.setDataSet(d1); 
					}else{
						//Create Param
						Param p = createParam(key, value);
						result.setParam(p);
					}
				}
			}
		}

		return result;
	}

	/**
	 * createRecord - Create Record from JSON String
	 * @param json
	 * @return
	 */
	public static Record createRecord(String json){

		Record record = new Record();

		if(json != null){
			json = json.trim();

			if(json.startsWith("[")){
				try {
					JSONArray jArray = new JSONArray(json);
					Dataset d = createDataset(jArray);
					d.setId("dataset");
					record.setDataset(d);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}else{
				try {
					JSONObject jObj = new JSONObject(json);
					record = createRecord(jObj);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}
		}

		return record;
	}

	/**
	 * createRecord - Create Record from JSON Object
	 * @param json
	 * @return
	 */
	public static Record createRecord(JSONObject json){
		Record record = new Record();

		if(json != null){
			Iterator<String> iter = json.keys();
			while(iter.hasNext()){
				String key = iter.next();

				Object value = json.opt(key);

				if(value != null){
					if(value instanceof JSONObject){
						ArrayList<Record> records = record.getRecords();

						//Create Record
						Record r1 = createRecord((JSONObject) value);
						r1.setID(key);
						records.add(r1);

					}else if(value instanceof JSONArray){
						//Create Dataset
						Dataset d1 = createDataset((JSONArray) value);
						d1.setId(key);
						record.setDataset(d1); 
					}else{
						//Create Param
						Param p = createParam(key, value);
						record.setParam(p);
					}
				}
			}
		}

		return record;
	}

	/**
	 * createDataset - Create Dataset from JSON String
	 * @param json
	 * @return
	 */
	public static Dataset createDataset(String json){

		Dataset dataset = new Dataset();

		if(json != null){
			json = json.trim();

			if(json.startsWith("[")){
				try {
					JSONArray jArray = new JSONArray(json);
					dataset = createDataset(jArray);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}else{
				try {
					JSONObject jObj = new JSONObject(json);
					JSONArray jArray = new JSONArray();
					jArray.put(jObj);
					dataset = createDataset(jArray);
				} catch (JSONException e) {
					//Ignore, not a JSON Object
				}
			}
		}

		return dataset;
	}

	/**
	 * createDataset - Create Dataset from JSONArray
	 * @param json
	 * @return
	 */
	public static Dataset createDataset(JSONArray json){
		Dataset dataset = new Dataset();

		if(json != null){
			int len = json.length();
			for(int i=0; i<len; i++){

				Object value = json.opt(i);

				Record r = new Record();

				if(value != null){
					if(value instanceof JSONObject){

						//Create Record
						r = createRecord((JSONObject) value);

					}else if(value instanceof JSONArray){

						//Create Dataset inside record
						Dataset d1 = createDataset((JSONArray) value);
						d1.setId("dataset");

						r.setDataset(d1);

					}else{
						//Create Param inside record
						Param p = createParam("param", value);
						r.setParam(p);
					}
				}

				//Add Record to Dataset
				dataset.setRecord(r);
			}
		}

		return dataset;
	}

	/**
	 * createParam - Create Param
	 * @param key
	 * @param value
	 * @return
	 */
	public static Param createParam(String key, Object value){
		Param p = null;
		if(key != null && value != null){

			String sValue = "";

			if(value instanceof Boolean){
				sValue = String.valueOf(value);
			}
			else if(value instanceof String){
				sValue = String.valueOf(value);
			}
			else{
				
				//BY handle the digits start with 0, to treat as string instead of number
				String strVal = String.valueOf(value);
				if(strVal != null && strVal.startsWith("0") && !strVal.contains(".")){
					logger.warn("BY value start with 0, treat as string for strVal=" + strVal);
					p = new Param(key, String.valueOf(value), "string");
					return p;
				}
				
				Double dValue = objectToDouble(value);
				if(dValue != null){
					//if((dValue % 1) == 0 ) {
					if(value.toString().indexOf('.') == -1) { 
						sValue = longToString(dValue.longValue());
					}else {
						sValue = doubleToString(dValue);
					}
				}else{
					sValue = String.valueOf(value);
				}
			}

			p = new Param(key, sValue, "string");
		}

		return p;
	}
	
	/**
	 * integerToString - Integer to String
	 * 
	 * @param value
	 * @return
	 */
	public static String integerToString(int value) {
		return Integer.toString(value);
	}
	
	/**
	 * doubleToString - Double to String
	 * @param d
	 * @return
	 */
	public static String doubleToString(Double d) {
		DecimalFormat formatter = new DecimalFormat("###################0.00################");
		String formmatedValue = formatter.format(d);
		return formmatedValue;
	}
	
	/**
	 * longToString - Long to String
	 * 
	 * @param value
	 * @return
	 */
	public static String longToString(long value) {
		return Long.toString(value);
	}

	/**
	 * objectToDouble - Object to Double
	 * @param value
	 * @return
	 */
	public static Double objectToDouble(Object value) {

		Double d = null;

		if(value instanceof Long){
			d = new Double((Long) value);
		}else if(value instanceof Integer){
			d = new Double((Integer) value);
		}else if(value instanceof Float){
			d = new Double((Float) value);
		}else if(value instanceof Double){
			d = new Double((Double) value);
		}else if(value instanceof String){
			try{
				d = new Double((String) value);
			}catch(Exception e){
				//Ignore
			}
		}

		return d;
	}

	public static void main(String args[]){
		//System.out.println(objectToString(null)); 
		System.out.println(objectToDouble("hello")); 
		System.out.println(doubleToString(objectToDouble("1.2345678E7"))); 
		System.out.println(doubleToString(objectToDouble(123123))); 
		System.out.println(doubleToString(objectToDouble(123123.9999))); 
		System.out.println(doubleToString(objectToDouble(.99999999)));
		System.out.println(doubleToString(objectToDouble(new Double(1231231231231231.9999D)))); 
		System.out.println(doubleToString(objectToDouble(new Long(1231231231231231L)))); 

		//	String stringjson = "{\n\t\t\"MYR\":{\n\t\t\t\"field1\":{\n\t\t\t\t\"mode\": \"independent\",\n\t\t\t\t\"dependencyField\" : \"\",\n\t\t\t\t\"mapping\" : {\n\t\t\t\t\t\"Default\" : {\n\t\t\t\t\t\t\"type\": \"dropdown\",\n\t\t\t\t\t\t\"name\": \"Purpose of Remittance\",\n\t\t\t\t\t\t\"dropDownList\" : {\n\t\t\t\t\t\t\t\"Family Expense/Savings\" : \"Family Expense/Savings\",\n\t\t\t\t\t\t\t\"Gift/Charity/Donations\" : \"Gift/Charity/Donations\",\n\t\t\t\t\t\t\t\"Travel\": \"Travel\",\n\t\t\t\t\t\t\t\"Education\": \"Education\",\n\t\t\t\t\t\t\t\"Salary\" : \"Salary\",\n\t\t\t\t\t\t\t\"Medical Fee\": \"Medical Fee\",\n\t\t\t\t\t\t\t\"Payment of Personal Bills/Invoices\" : \"Payment of Personal Bills/Invoices\"\n\t\t\t\t\t\t}\t\t\t\n\t\t\t\t\t}\n\t\t\t\t}\t\t\n\t\t\t},\n\t\t\t\"field2\":{\n\t\t\t\t\"mode\": \"dependent\",\n\t\t\t\t\"dependencyField\" : \"field1\",\n\t\t\t\t\"mapping\" : {\n\t\t\t\t\t\"Travel\" : {\n\t\t\t\t\t\t\"type\": \"dropdown\",\n\t\t\t\t\t\t\"name\": \"Destination\",\n\t\t\t\t\t\t\"dropDownList\" : {\n\t\t\t\t\t\t\t\"\":\"\"\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\t\"Education\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Institution or Student Name\"\n\t\t\t\t\t},\n\t\t\t\t\t\"Salary\" : {\n\t\t\t\t\t\t\"type\": \"dropdown\",\n\t\t\t\t\t\t\"name\": \"Month\",\n\t\t\t\t\t\t\"dropDownList\" : {\n\t\t\t\t\t\t\t\"\":\"\"\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\t\"Payment of Personal Bills/Invoices\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Billing Organization Name\"\n\t\t\t\t\t},\n\t\t\t\t\t\"Default\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Payment Details for Recipient (if any) 1\"\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"field3\":{\n\t\t\t\t\"mode\" : \"dependent\",\n\t\t\t\t\"dependencyField\" : \"field1\",\n\t\t\t\t\"mapping\":{\n\t\t\t\t\t\"Salary\" : {\n\t\t\t\t\t\t\"type\" : \"dropdown\",\n\t\t\t\t\t\t\"name\" : \"Year\",\n\t\t\t\t\t\t\"dropDownList\" : {\n\t\t\t\t\t\t\t\"\":\"\"\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\t\"Payment of Personal Bills/Invoices\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Billing Reference Number\"\n\t\t\t\t\t},\n\t\t\t\t\t\"Default\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Payment Details for Recipient (if any) 2\"\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"field4\":{\n\t\t\t\t\"mode\": \"independent\",\n\t\t\t\tdependencyField : \"\",\n\t\t\t\t\"mapping\" :{\n\t\t\t\t\t\"Default\" : {\n\t\t\t\t\t\t\"type\" : \"text\",\n\t\t\t\t\t\t\"name\" : \"Payment Details for Recipient (if any) 3\"\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t}}";

		try {
			String stringjson =  "";
			System.out.println(stringjson);
			JSONObject obj = new JSONObject(stringjson);

			System.out.println(convert(createResult(obj))); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * copyJsonKeyValuePairsToNewMap - Copy JSON Key Value pairs to HashMap
	 * @param map
	 * @param jsonMapKey
	 * @return
	 */
	public static HashMap copyJsonKeyValuePairsToNewMap(HashMap map, String jsonMapKey){
		HashMap detailInput = new HashMap();
		detailInput.putAll(map);
		
		// Copy JSON key-value pair to map
		copyJsonKeyValuePairsToMap(detailInput, map, jsonMapKey); 
		
		// Remove securityKey from input
		detailInput.remove(jsonMapKey);
		
		return detailInput;
	}
	
	/**
	 * copyJsonKeyValuePairsToMap - Copy key value pair to existing map
	 * @param detailInput
	 * @param map
	 * @param jsonMapKey
	 */
	public static void copyJsonKeyValuePairsToMap(HashMap detailInput, HashMap map, String jsonMapKey){
		String securityKeyJson = (String) map.get(jsonMapKey);
		try{
			if(!securityKeyJson.isEmpty()){
				JSONObject keyJson = new JSONObject(securityKeyJson);
				Iterator<String> iter = keyJson.keys();
				while(iter.hasNext()){
					String key = iter.next();
					detailInput.put(key, keyJson.opt(key)); 
				}	
			}
		}
		catch(Exception e){
			// Ignore
		}
	}
	
	public static Result createResult(JSONObject json , Result result){

		if(json != null){
			Iterator<String> iter = json.keys();
			while(iter.hasNext()){
				String key = iter.next();

				Object value = json.opt(key);

				if(value != null){
					if(value instanceof JSONObject){
						ArrayList<Record> records = result.getRecords();

						//Create Record
						Record r1 = createRecord((JSONObject) value);
						r1.setID(key);
						records.add(r1);

					}else if(value instanceof JSONArray){
						//Create Dataset
						Dataset d1 = createDataset((JSONArray) value);
						d1.setId(key);
						result.setDataSet(d1); 
					}else{
						//Create Param
						Param p = createParam(key, value);
						result.setParam(p);
					}
				}
			}
		}

		return result;
	}

}
