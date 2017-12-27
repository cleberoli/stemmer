# Stemming
Implementation of some stemming algorithms implemented as the origianl proposals, and using deterministic finite automata in a level of character and bits. The current version of the project is test for only English language.

## Running the tests
To run the tests available you only need to run the StemmerTest with the following arguments:
```
<language> <algorithm> <input file> -o <output file> [-t <interval>]
```
where _language_ is **english**, **portuguese** or **spanish**; _algorithm_ can be **bsl** (baseline) or **ads** (automata driven stemming); and _interval_ measures the stemmer's throughput in milliseconds.

## Contributors
* Cleber Oliveira
* Wladmir Brandao

## License
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
```
http://www.apache.org/licenses/LICENSE-2.0
```
