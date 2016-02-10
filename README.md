# Evacuation_JavaSwing

QUICK START


TO DEMO TYPE
'java Main    doorSize    noEvacuees    speed'

Arguments can take ranges

'doorSize' [30 - 400] average evacuee size is 15 so door must take a larger value, average single door size is 30, average emergency door size 60

'noEvacuees' [0 - 500] room is 500 pixels and evacuee is 15 plus a personal space

'speed' [1 - 4] the simulation is set to real time, a walk would be 1 , a paniced exit will takes speeds 2 - 4. 
With a room filled to more than half its capacity an exit speed would never be larger than 2

to run the simulation faster remove the sleep method on line 44 in 'EvacuationPanel.java' and set frame show to false on line 31 in 'Main.java'

##############################################################################################################################################################

##############################################################################################################################################################


HOW IT WORKS


The project used the concept of a cellular automata. My room was set to a size of 500 by 500 pixels and evacuees were allowed to move towards a door of finite size (the initial model had a door size of 30) positioned in the centre of the left wall. Each evacuee was represented by a 2D oval of diameter 15 and the walls of the room were also made using multiple 2D rectangles. All values stated here are measured in screen units.

The following rules have been implemented in my program:

•	Each evacuee can move as far as its fixed speed allows it (initially set to 1) but stops after a collision with a wall or another evacuee
•	On collision with another evacuee the current evacuee has a probability of turning left (0.75) , a probability of turning right (0.75) and a probability of moving backwards (0.5) 
•	On collision with a wall the evacuee will turn 90 degrees in the direction of the door however if this space is occupied it will remain still
•	Each evacuees initial movement vector is changed every time step and is towards a random point contained within the evacuee penetrable part of the door.
•	Each evacuee is moved in a random order each time step
•	An impatient evacuee (i.e. an evacuee that hasn't moved for 11 time steps) will reduce its size by 1 (initially the evacuee size is set to 15) if he still hasn't moved after one reduction a second reduction of 1 is made
the time step threshold value will be explained in the method

•	An aggressive evacuee (i.e. an evacuee that hasn't moved 30 time steps and is position in close proximity to the exit) will reduce its size once more then move 2 normal steps. Its size will then be reset.
the time step threshold value will be explained in the method


CLASSES

MyVector class: this contained all vector manipulation methods needed so that component of the vectors would not to be broken open within other classes.

Wall class: contains one class variable which is the wall thickness and is called when object Room is created.

Evacuee class: this contained methods to make movement decisions on interactions with the room and evacuees and methods checking the interaction between individual evacuees . Evacuee() took arguments position and speed  because this is specific to the rooms dimensions and the experiment in question. plantingCollsion() would return true if the current evacuee position is occupied and is called when Room.fillRoom() is run. evacueeCollsion() would return true if the evacuees next position obtained using the lookAhead() method is occupied. This enables the evacuee to look ahead as real person would do without having to move and then check. This is called when Room.haveIHitEvacuee() is run. bounceOfWall() and collisionDecision() implement changes to the movement vector simulating the decision making process of the evacuee. These are outlined thoroughly in section 4.0 on the Model Description.

Room class: this contains the methods considering the room and evacuee interactions and methods looking at the evacuees as a crowd rather than a single person.
Room() takes arguments for evacuee speed, door size and number of evacuees and also implements the fillRoom() when created. whereIsTheDoor() and hitWall() are all decisions influenced by the room are tied together in the rooms move() method which is outlined thoroughly in section 4.0 on the Model Description. haveIHitEvacuee() runs through a loop of all evacuee's present inside the room and calls on the evacueeCollsion() method for each one.

EvacuationPanel class: simply uses a paint component and getter methods from class Room to construct a display containing multiple evacuees and a wall.

evacuationFrame class : this sets the frame to an appropriate size and adds the evacuationPanel to it.

Experiment class: this contains both a room constructor and a frame constructor which takes the room as an argument enabling the simulation to be run without graphics. The two methods class Experiment implements are: EvacuationPanel.update() which calls on the repaint method in evacuationPanel  setting the evacuee graphics to there new positions and Room.move() which essential simulates one time step. 
Experimental data was stored in array list's owned by the experiment class and data recordings for the time taken to evacuate was initiated when the Room class variable allFree was set to true by counting the number of evacuees left in the room.

MODEL DESCRIPTION

Although the graphics represents a cellular automata the essence of the program uses vectors with decimal point values. Therefore in the execution, values have been rounded so that the output creates a cellular automata. This creates a randomness in the path the evacuee takes between its starting cell and its target cell which in turn simulates the panic of the evacuee and furthers the reliability of the simulation. 


POPULATING THE ROOM

When the room is constructed it will produce a finite number of object Evacuee represented by an oval GUI. Each are given class variables: position and movement which are vectors and an evacuee size and speed. The position vector is initialised in the evacuee constructor and is given random components.

position  = ∑ xiei 
          
xi  = (evacuee size i /2) +  Math.random() * (Room dimension i - evacuee size i )

Assigning one iteration as one second I obtained the following conversions to a real evacuee.
Evacuee() value	Realistic value	Scale Factor
Evacuee size	15 units	0.6 m	0.04m
speed	1 units	0.04 m/s	0.04 m

The position vector initialisation is implemented again for that specificevacuee if 
(a) plantedCollision(a,b) returns true. plantedCollision(a,b) 
returns true for any neighbouring evacuee 
(b) if the argument evacuees 
a and b  have the positions p1 and p2 such that     

RADOMISED ORDER

Firstly the scrambleArray() is implemented causing even permutations to occur in the array of evacuees which in turn reorders it randomly. My methodology can be explained using an array of integers. The algorithm first selects the last component in the array (1 2 3 4 5) and then picks random element with an indices lower or equal to that of the element selected earlier (1 2 3 4 5) . Once selected it swaps the yellow element with the chosen green element and then repeats this process with the second to last element (1 2 3 4 5) (1 2 3 4 5). This keeps on repeating working towards lowering the indices of the yellow elements until the first element gets swapped with itself.

MOVEMENT UPDATES

The current evacuee's movement vector is then set using whereIsTheDoor()  to be in the direction of a random point in the "evacuee penetrable" section of the door way. 

movement  = ∑ siei  - position

s1 = - wall thickness          s2 = (dimension 2 /2) - (door size  /2) + (evacuee size 2 /2) + 
Math.random()*(door size  - evacuee size 2 )

SPEED

The rest of the evacuee movement method is then placed in a loop so that it is run for different speeds. Each evacuee has an unimpeded walking speed speed; the speed at which a realistic evacuee would like to escape at. In one time step if the evacuee cannot move to some chosen destination speed * time step away (yellow ring) from its starting position it will then check the availability of destinations (speed - 1)*time step away (black ring). This repeats until its speed is zero at which point it just doesn't move at all.

INTERACTIONS

HaveIHitEvacuee() and HitWall() use the Evacuee.lookAhead() which returns position + movement.normalise()*speed  enabling the evacuee to check to see whether another evacuee lies within its path. If HaveIHitEvacuee() returns true the current evacuee has a probability of 0.75 of turning right, if its still true there is a probability of 0.75 to turn left and if its still true there is a probability of 0.5 for the evacuee to move backwards. If its still true after this the method sets a           movement = (0,0). This decision making process is analogous to a real human in the sense that they will be quite agile 75% of the time and try an run around other evacuees and sometimes patient 50% of the time because they know if they panic to much it will lead to a blockage. 

hitWall() also uses the lookAhead() method to check whether the new position lies outside the room. i.e. if position + movement.normalise()*speed    = ∑ xiei

(evacuee size /2)< x1<( dimension1 ) - (evacuee size /2)
(evacuee size /2)< x2<( dimension2 ) - (evacuee size /2)

If hitWall() returns true depending on which side of the door its on the evacuee turns in the direction of the door, if another evacuee lies in its new position its movement = (0,0) and it won't move at all. The is realistic since an evacuee would also be trying to get closer to the door. Turning transformations are done by multiplying the movement vector by a matrix shown bellow where   is the rotation in a anticlockwise direction.
  

IMPATIENCE

Once outside the loop an impatience value is set for the evacuee. The impatience is initially zero and every time the evacuees decided movement = (0,0) impatience = impatience + 1 . If the evacuee doesn't move for over 10 time steps its size reduces by 1 (coloured blue) if it still doesn't move on the following time step the evacuee size then reduces again by 1 (coloured black). The size is then kept at this value until they escape. This is identical to a real evacuation situation because your impatience would never improve and you would continue to attempted to squeeze through the crowd with the same diminished size. For an evacuee to become aggressive (coloured red)  there first needs to be a real sense of impatience and secondly a sense of competition for the exit.  Because of this an evacuee will only become aggressive when the exit is in sight. i.e. when  position  p  = ∑ xiei

x1 < 20 you can approximately see past one person
(dimension 2 /2 )-(door size /2)  < x2 < (dimension 2 /2) +(door size /2)

An evacuee will also only become aggressive after an impatience value reaches 30 which was decided through observation so that a realistic amount (approx 3) of aggressive evacuees would be produced at one instance. Once aggressive the evacuee will reduce its size again by 1, move twice its speed*time step distance and then it will increase its size to normal on the next time step. This enables it to enter groups of other evacuees and then expand them by increasing in size. It is inevitable that after one aggressive time step the evacuee is not going to move since its in a nest of other evacuees, thereby not changing its overall speed. Identically to a real evacuation it will remain aggressive until its escaped.

moveEvacuee() implements position = position + m.normalise()*speed  

EXIT

After the evacuee has passed halfway through the corridor that element in the array is set to null to leave a clear corridor for the exodus following it. 

BLOCKAGES

Since the aggressive method has been added blockages are rare since an arch of people can easily be broken by someone pushing. Because of this the only way blockages occur is if there is a fight at the door between exactly 3 evacuees. This is evident in the output because it returns an exit time of the maximum value 10000.



















