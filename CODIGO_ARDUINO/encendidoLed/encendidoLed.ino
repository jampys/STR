
const int LED_Rojo = 13;
//const int LED_Amarillo=12;
int inByte = 0;
 
void setup(){
    Serial.begin(9600); //Open the serial port
//    pinMode(LED_Amarillo, OUTPUT);
    pinMode(LED_Rojo, OUTPUT);
//    digitalWrite(LED_Amarillo, LOW);
    digitalWrite(LED_Rojo, LOW);
  }
 
void loop(){
 
    if(Serial.available() > 0){  //si el puerto serie esta disponible
 
        inByte = Serial.read(); //aca se lee lo que transmitiremos desde java
//        Serial.println(inByte);
//        if(inByte == '0')
//            digitalWrite(LED_Amarillo, LOW);
//        else if(inByte=='1')
//            digitalWrite(LED_Amarillo, HIGH);
        if(inByte=='0')
            digitalWrite(LED_Rojo, LOW);
        else if(inByte=='1')
            digitalWrite(LED_Rojo, HIGH);
    }
}

