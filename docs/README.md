## Bio4j documentation

### API docs

- **Literate programming style documentation**: under [docs/src](src/) You will find here the Bio4j model code together with how this corresponds to the integrated data sources, the meaning of vertices and relationships, etc.
- **Javadocs** If you _really_ want them, they are under `http://bio4j.com/bio4j/docs/api/<version>/`

### Use Bio4j

In not so precise terms, Bio4j is both an API and a database; this means that writing code using that API and executing it against a particular Bio4j distance are completely independent tasks.

#### Using Bio4j in your code

_TODO_

#### Importing and using Bio4j data

We provide a pre-imported full Bio4j Titan distribution, available through S3. If you can use Amazon Web Services (or is already part of your infrastructure) this your best option: you don't need to worry about loading data, and you can easily spin an EC2 instance with everything already configured and ready to use. The docs for this:

- [Bio4j and AWS](bio4j-and-aws.md)

If for whatever reason you cannot (or just don't want to) use AWS, you can always download the raw data and run the import code on your own infrastructure. If that's what you want:

- [Bio4j on your own](bio4j-on-your-own.md)

<!-- Old stuf: do something with it

* [Getting started](getting-started.md)
* [Domain model](domain-model.md)
* [Bio4j modules](bio4j-modules.md)
* [Importing Bio4j](importing-bio4j.md)
* Entry points and Indexing:
  - [Auxiliary relationships](auxiliary-relationships.md)
  - [Node indexing](node-indexing.md)
* [FAQ](faq.md)
* [Bio4j 0.8 javadocs](http://bio4j.com/docs/bio4j/apidocs/)
* [Examples](examples.md)
 -->