import tkinter as tk

class Application(tk.Frame):
    def __init__(self, master=None):
        tk.Frame.__init__(self, master)
        self.pack()
        self.createWidgets()

    def createWidgets(self):
        self.hi_there = tk.Button(self)
        self.hi_there["text"] = "Hello World\n(click me)"
        self.hi_there["command"] = self.say_hi
        self.hi_there.pack(side="top")

        self.QUIT = tk.Button(self, text="QUIT", fg="red",
                                            command=root.destroy)
        self.QUIT.pack(side="bottom")

    def say_hi(self):
        print("hi there, everyone!")

root = tk.Tk()
app = Application(master=root)
#app.mainloop()






root = tk.Tk()
frame = tk.Frame(root)
frame.pack()

bottomframe = tk.Frame(root)
bottomframe.pack( side = "bottom" )

redbutton = tk.Button(frame, text="Red", fg="red")
redbutton.pack( side = "left")

greenbutton = tk.Button(frame, text="Brown", fg="brown")
greenbutton.pack( side = "left" )

bluebutton = tk.Button(frame, text="Blue", fg="blue")
bluebutton.pack( side = "left" )

blackbutton = tk.Button(bottomframe, text="Black", fg="black")
blackbutton.pack( side = "bottom")

root.mainloop()




root = tk.Tk()
thing = tk.Frame(root)
thing.pack()

bottomframe = tk.Frame(root)
bottomframe.pack( side = "bottom" )

redbutton = tk.Button(thing, text="Red", fg="red")
redbutton.pack( side = "left")

greenbutton = tk.Button(thing, text="Brown", fg="brown")
greenbutton.pack( side = "left" )

bluebutton = tk.Button(thing, text="Blue", fg="blue")
bluebutton.pack( side = "left" )

blackbutton = tk.Button(bottomframe, text="Black", fg="black")
blackbutton.pack( side = "bottom")