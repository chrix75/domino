
create constraint on (task:Task) assert task.name is unique;
create constraint on (template:Template) assert template.uuid is unique;

