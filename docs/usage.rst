Usage
=====
Currently there is only one command available: ``file``

File
----
The command ``file`` currently has four arguments, one of which is optional.
To be precise, the command consists of these blocks:

.. code-block:: none

   file [-p/--pretty-print] <inputfile> <outputfile> <language>

The arguments mean in detail:

*  ``-p`` or ``--pretty-print``:

   Write the output in pretty print format. (JSON Beautify)

*  ``inputfile``:

   The file to be translated.

*  ``outputfile``:

   The file in which the translated json should be saved.

*  ``language``:

   The language into which to translate. (Country code)

And last but not least, a few working examples:

.. code-block:: none

   file -p language-en.json language-de.json DE
   file language-fr.json language-it.json IT
   file --pretty-print language.json language.json DE

This command can also be executed in headless mode:

.. code-block:: none

   java -jar dljt-jar-with-dependencies.jar file language-en.json language-de.json DE
