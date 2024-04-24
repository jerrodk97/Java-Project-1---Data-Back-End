//Jerrod Koenigseder
package guiProject;

public class Instance {

	private String condition;
	private int temperature;
	private int humidity;
	private String windy;
	private String activity;
	
	public Instance()
	{
		condition = "not set";
		temperature = -1;
		humidity = -1;
		windy = "false";
		activity = "not set";
	}
	
	public Instance(String c, int t, int h, String w, String a) {
		condition = c;
		temperature = t;
		humidity = h;
		windy = w;
		activity = a;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String c) {
		condition = c;
	}
	public int getTemp() {
		return temperature;
	}
	public void setTemp(int t) {
		temperature = t;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int h) {
		humidity = h;
	}
	public String getWindy() {
		return windy;
	}
	public void setWindy(String w) {
		windy = w;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String a) {
		activity = a;
	}
	public String toString() {
		return condition+","+temperature+","+humidity+","+windy+","+activity+"\n";
	}
	public boolean equals(Instance i) {
        if (temperature == i.getTemp() && humidity == i.getHumidity() && windy.equals(i.getWindy()) && condition.equals(i.getCondition()) && activity.equals(i.getActivity())) {
            return true;
        }
        return false;
    }
}
