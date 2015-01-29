void setup(){
  Serial.begin(9600);
}

void loop(){
  Serial.println("Hola mundo!!!"); //envia mensaje por puerto serie
  delay(1000);
}
