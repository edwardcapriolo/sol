# Sol - Serializable Object Language 
SOL can be used in numerous ways. One of the more common is to describe some function that will
operate on some data in once system and have that operation carried out on another. 

SOL uses prefix notation (think lisp/closure) to avoid complexity of order of operations parenthesis.

Does 5 equal "5"?
```declarative
Sol sol = new Sol();
sol.compileAsSupplier(
    equal(lit("5"), lit(5L)))
.get());
```
Does an instance of SomePojo have a field "username" with a value equal to "bob"?
```declarative
Sol sol = new Sol();
sol.compile(
  equal(field("username"), lit("bob"))
).apply(new SomePojo()))
```

SOL doesn't only work on Java Objects, it can also operate on JSONNode, a spark struct, etc. 
There are a few challenges that crop up:

- An implementation has to be provided for some operators in the Sol language, like field or col.
- JSON typing is not the same as Java typing thus sometimes "casting" using "as" is required. 


SOL was inspired in part by LINQ (https://learn.microsoft.com/en-us/dotnet/csharp/linq/). 

