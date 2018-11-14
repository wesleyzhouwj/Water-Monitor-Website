var obj= {"data":[{}]}

var arr = new Array();
// arr[0] = new Array(Date.UTC(2008, 0, 1),-1);
// arr[1] = new Array(Date.UTC(2009, 1, 2),-2);

arr.push([Date.UTC(2008, 0, 1),-1]);
arr.push(new Array(Date.UTC(2009, 1, 2),-2));
console.log(arr);

console.log(Date.UTC(2010, 0, 1));

