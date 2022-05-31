#include <ESP8266WiFi.h>
#include "DHT.h"
#define dht11TYPE DHT11 
#define dht11PIN D2
DHT dht11(dht11PIN, dht11TYPE);
#include <PubSubClient.h>

const char* ssid = "HUAWEI-4B34";
const char* password = "01285353";

const char* host = "script.google.com";
const int httpsPort = 443;

unsigned long lastMsg = 0;

String id = "AKfycbxifRAeuvxRduab6tZ71JVi3JH1kFkr3XKds3dloradRtzm7DAKCyh6mvKD-2g1Zgda";

#define MUX_A D4
#define MUX_B D3
#define MUX_C D1

#define ANALOG_INPUT A0

WiFiClientSecure client;

void setup_wifi() {

  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}


void connect() {
  while (client.connected()) {
    String line = client.readStringUntil('\n');
    if (line == "\r") {
      Serial.println("headers received");
      break;
    }
  }
  if (!client.connect(host, httpsPort)) {
    Serial.println("connection failed");
    return;
  }
}
 
void setup() {

  //................temperature......
  
  Serial.begin(9600);
  Serial.println(" DHT11 Test Pass ");
  dht11.begin();
  

  //.................end............

  //................(moisture/rain)_analog_sensor.........

  //Deifne output pins for Mux
  pinMode(MUX_A, OUTPUT);
  pinMode(MUX_B, OUTPUT);     
  pinMode(MUX_C, OUTPUT); 

 //..................end..............
 setup_wifi();
  client.setInsecure();
}

void changeMux(int c, int b, int a) {
  digitalWrite(MUX_A, a);
  digitalWrite(MUX_B, b);
  digitalWrite(MUX_C, c);
}

void loop() {

connect();

 

//...............................................reading the temperature from the sensor
 
  int chk = dht11.read(dht11PIN);

  float t = dht11.readTemperature();
  String temperatureVal =  String(t);

   delay(2000);
    
    

   float h = dht11.readHumidity();
    String humidityVal =    String(h);
    
    delay(2000);
//................................................end...................................


//.........................Multiplexer.................................................

 float value;
  
  changeMux(LOW, LOW, LOW);
  value = analogRead(ANALOG_INPUT); //Value of the sensor connected Option 0 pin of Mux
  value=(value/1024)*100;
  String rainVal =  String(value);
  
delay(2000);
  
  changeMux(LOW, LOW, HIGH);
  value = analogRead(ANALOG_INPUT); //Value of the sensor connected Option 1 pin of Mux
  value=(value/1024)*100;
  String moistureVal = String(value) ;
  
delay(2000);
  
  changeMux(LOW, HIGH, LOW);
  value = analogRead(ANALOG_INPUT); //Value of the sensor connected Option 2 pin of Mux
  value=(value/1024)*100;
  String lightVal = String(value);
 
  //..........................end.....................................................




  unsigned long now = millis();
  if (now - lastMsg > 6000) {
    lastMsg = now;

  String Datasend =  temperatureVal;
  Serial.println(Datasend);  
    String url = "https://script.google.com/macros/s/" + id + "/exec?temperature=" + temperatureVal+ "&humidity=" + humidityVal+ "&moisture=" + moistureVal+ "&rain=" + rainVal+ "&light-intencity=" + lightVal;
    Serial.print("Sent message: ");
    Serial.println(url);
      client.print(String("GET ") + url + " HTTP/1.1\r\n" +
         "Host: " + host + "\r\n" +
         "User-Agent: BuildFailureDetectorESP8266\r\n" +
         "Connection: close\r\n\r\n");
  }

}
