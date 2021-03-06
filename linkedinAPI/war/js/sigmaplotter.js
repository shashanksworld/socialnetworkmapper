
function createSignmaContainer()
{
	var s= new sigma('container');
	return s;
}

function plotNodes()
{
	obj=createSignmaContainer();
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
				   size: getNodeSize(connArray[i]),
				   color:'#'+Math.floor(Math.random()*16777215).toString(16)
				 });
			
			} 	
	obj.refresh();
	dom = document.querySelector('#container');
	dom.addEventListener('mousemove', function(e) {
	    mouseX = sigma.utils.getX(e);
	    mouseY = sigma.utils.getY(e);
	  }, false);
	
	obj.bind("clickNode",function(e){console.log("node clicked....."+e.data.node.label);});
	
}




function getX(node)
{
	var num=parseInt(node["num-connections"]);
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
	if(isNaN(num))
		{console.log("NAN with::"+num+"::"+node["num-connections"]);
			return 500;
		}

		if(num>450)
		{
			return genRandom(0,100);
		}
		else if(num>250 && num<449)
			return genRandom(100,150);
		else if(num>100 && num<250)
			return genRandom(150,250);
		else if(num<100)
			return genRandom(250,400);
		else
			return genRandom(400,500);
			
		}
function getNodeSize(node)
{
	var num=parseInt(node["num-connections"]);
	if(isNaN(num))
		return 0;

		if(num>499)
		{
			return 3;
		}
		else if(num>250 && num<499)
		{
			return 2;
		}
		else if(num<249)
		{
			return 1;
		}	
}

function genRandom(start,end)
{ var rand=Math.floor((Math.random() * end) + start);
return rand+2;
}

/*
function getMean()
{
	var sum=0;
	for(j=0;j<jsonData.person.length;j++)
	{
		sum=sum+NaN(jsonData.person[j]["num-connections"])?0:jsonData.person[i]["num-connections"];
	}
	var mean=sum/(jsonData.person.length-1);
	appendData("connections","Average No of 2nd layer connection: "+mean);
	

}
function appendData(id,data)
{
	var schema=document.getElementById(id).innerHTML;
	document.getElementById(id).innerHTML=schema+data;
}

function fillData(id,data)
{
	document.getElementById(id).innerHTML=data;

}
*/
