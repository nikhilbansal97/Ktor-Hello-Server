package com.nikhil.utils.exceptions

class MissingFieldsException(missingField: String) : Throwable("Missing $missingField")