Configuration
=============

The configuration file looks like this:

.. code-block:: json

   {
     "debug": false,
     "apiToken": "token",
     "apiDomain": "api-free.deepl.com"
   }

The individual values mean:

*  ``debug``:
   Should the application be run in debug mode?
   (Additional information is then displayed while it is being executed.
   Useful if something does not work as it should)

*  ``apiToken``:
   Here comes your personal DeepL api token.
   You can call it up `here <https://www.deepl.com/en/pro-account/summary>`__, or if you don't
   have one yet, create one `here <https://www.deepl.com/pro/change-plan#developer>`__.

*  ``apiDomain``:
   Where should the queries be sent? If you are using DeepL Free it should be "api-free.deepl.com".
