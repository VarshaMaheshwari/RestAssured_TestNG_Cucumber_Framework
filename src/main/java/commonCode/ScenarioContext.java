package commonCode;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext{
    Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext=new HashMap<String,Object>();
    }
    public Object getContext(String key){
      return scenarioContext.get(key);
    }
    public void setContext(String key, Object value){
         scenarioContext.put(key, value);}

    public boolean isContains(String key){
      return  scenarioContext.containsKey(key);
    }
}