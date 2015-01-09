/**
 * author:shashanksworld
 * version:1.1 
 */

 

function createSignmaContainer()
{
var s= new sigma('container');
return s;
}

function plotNodes()
{
	var obj=createSignmaContainer();
		var connArray=jsonData.person;
	//jsonData.person.length
	for(i=1;i<200;i++)
			{
			obj.graph.addNode({
				   // Main attributes:
				   id: i.toString(),
				   label: connArray[i]["first-name"]+"-"+connArray[i]["last-name"]+"["+connArray[i]["num-connections"]+"]",
				   // Display attributes:
				   x: 0+ getX(connArray[i]),
				   y: 0+ getY(connArray[i]),
				   size: 1,
				   color:'#'+Math.floor(Math.random()*16777215).toString(16)
				 })
			
			}
	obj.refresh();
	
}

function getX(node)
{
	var num=parseInt(node["num-connections"]);
	console.log(num);
	if(isNaN(num))
		return 500;
	if(num>450)
		{
			return genRandom(200,400);
		}
	else if(num>250 && num<400)
		return genRandom(100,500);
	else if(num>100 && num<250)
		return genRandom(50,550);
	else if(num<100)
		return genRandom(0,600);
	else
		return genRandom(380,400);
		
}
function getY(node)
{
	var num=parseInt(node["num-connections"]);
	console.log(num);
	if(isNaN(num))
		return 500;

		if(num>450)
		{
			return genRandom(0,100);
		}
		else if(num>250 && num<400)
			return genRandom(100,150);
		else if(num>100 && num<250)
			return genRandom(150,250);
		else if(num<100)
			return genRandom(250,400);
		else
			return genRandom(400,500);
			
		}

function genRandom(start,end)
{ var rand=Math.floor((Math.random() * end) + start);
return rand+2;
}

