#include <unistd.h>            
#include <string.h>            
#include <ctype.h>             
#include <stdio.h>             
#include <stdlib.h>            
#include <sys/types.h>              
#include <signal.h>            
#include <readline/readline.h>
#include <stdlib.h>
#include <sys/wait.h>
#define INPUT_SIZE 64
#define LSH_TOK_DELIM " \t\r\n\a"
int lineSize = 0;
int bg_count = 0;
typedef struct node{
	pid_t pid;
	char* path;
	int stat;
	struct node* next;
	
}node;
node *process1 = NULL;
int checkProcess(pid_t pid)
{
	node* temp = process1;
	while(temp!=NULL)
	{
		if (temp->pid ==pid)
			return 1;
		temp = temp->next;
	}
	return 0;
}
void addToList(pid_t pid, char* path, int sta)
{
	node* process = (node*)malloc(sizeof(node));
	process->pid = pid;
	process->path = path;
	process->stat = sta;
	process->next = NULL;
	if(process1==NULL)
	{
		process1=process;
	}
	else
	{
		node* temp0 = process1;
		while(temp0->next!=NULL)
		{
			temp0 = temp0->next;
		}
		temp0->next = process;
	}
	bg_count++;
}
void removeFromList(pid_t pid)
{
	if(checkProcess(pid)==0)
	{
		return;
	}
	node* temp1 = process1;
	node* temp2 = NULL;
	
	while(temp1 !=NULL)
	{
		if(temp1->pid ==pid)
		{
			if(temp1==process1)
			{
				process1 = process1->next;
			}
			else
			{
				temp2->next =temp1->next;
			}
			free(temp1);
			bg_count--;
			return;
		}
		temp2 = temp1;
		temp1 = temp1->next;
	}
	
}



void bg(char** input)
{
	pid_t pid = fork();
	int sta =1;
	if(pid == 0) //child
	{
		char* command = input[1];
		execvp(command,&input[1]);
		printf("Error: failed to execute command %s\n", command);
		exit(1);
	}
	else if(pid>0) //parent
	{
		char* path = (char*)malloc(sizeof(char)*INPUT_SIZE);
		realpath(input[1],path);
		addToList(pid,path,sta);
	}
	else
	{
		printf("Error: fail to fork\n");
	}
	
}
void killProcess(pid_t pid, int id)
{
	int error;
	if(checkProcess(pid)==0)
	{
		printf("Error: invalid pid\n");
		return;
	}
	if(id ==1)
	{
		error= kill(pid, SIGTERM);
	}
	else if(id ==2)
	{
		error = kill(pid, SIGSTOP);
	}
	else if(id ==3)
	{
		error = kill(pid, SIGCONT);
	}
	if(error<0)
	{
		sleep(1);
	}
	else if(error ==0)
	{
		removeFromList(pid);
		printf("kill succuess\n");
	}
	else
	{
		printf("Error: failed to execute bgkill\n");
	}
}
void bglist()
{
	
	node* temp3 = process1;
	while(temp3!=NULL)
	{
		printf("%d:\t  %s\n",temp3->pid, temp3->path);
		temp3 = temp3->next;
	}
	printf("Total background jobs: %d\n", bg_count);
}
void pstat(pid_t pid)
{
	
	
}





int input(char** userInput)
{
	char* header = "PMan: >";
	char* line = readline(header);
	if(strcmp(line,"")==0)
	{
	return 0;
	}
	char* token=strtok(line," ");
	int position;
	for(position =0; position<INPUT_SIZE;position++)
	{
		userInput[position] = token;
		token = strtok(NULL," ");
	}
	return 1;
}

void identify(char** command)
{
	char* cmd = command[0];
	if(strcmp(cmd, "bg")==0)
	{
		bg(command);
	}
	else if(strcmp(cmd,"bglist")==0)
	{
		bglist();
	}
	else if(strcmp(cmd,"bgkill")==0)
	{
		int pid = atoi(command[1]);
		if(pid==0)
		{
			printf("invalid input on pid\n");
			return;
		}
		else
		{
			killProcess(pid,1);
		}
	}
	else if(strcmp(cmd,"bgstop")==0)
	{
		int pid = atoi(command[1]);
		if(pid==0)
		{
			printf("invalid input on pid\n");
			return;
		}
		else
		{
			killProcess(pid,2);
		}
	}
	else if(strcmp(cmd,"bgstart")==0)
	{
		int pid = atoi(command[1]);
		if(pid==0)
		{
			printf("invalid input on pid\n");
			return;
		}
		else
		{
			killProcess(pid,3);
		}
	}
	else if(strcmp(cmd,"pstat")==0)
	{
		int pid = atoi(command[1]);
		if(pid==0)
		{
			printf("invalid input on pid\n");
			return;
		}
		else
		{
			killProcess(pid,3);
		}
	}
	else
	{
		printf(" command not found\n");
	}
	
}


int main(void){
	while(1){
		char* userInput[INPUT_SIZE];
		int reply = input(userInput);
		if(reply)
		{
			identify(userInput);
		}
	}
	
	
	
	
	
	
	return 0;
}
