default: Driver.java HouseMap.java Room.java 
	javac Driver.java HouseMap.java Room.java 

run: Driver.class HouseMap.class Room.class 
	java Driver

clean:
	rm -f *.class