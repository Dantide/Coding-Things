Definition x := 42.
Definition y := 3110.
Definition z := 0.
Check x.
Print x.

Definition inc x := x + 1.
Print inc.

Definition inc' := fun x => x + 1.
Print inc'.

Definition xplus1 := inc x.
Print xplus1.
Compute xplus1.

Definition sum3 (x y z : nat) : nat :=
  x + y + z.
Check sum3.

Compute fst (3,4).

Definition add_pair p := 
  match p with
  | (x, y) => x + y
  end.

Require Import List.
Import ListNotations.

Definition lst := [1;2;3].
Compute lst.

Compute length lst.
Compute map inc lst.

Inductive day : Type :=
| sun : day
| mon : day
| tue : day
| wed : day
| thu : day
| fri : day
| sat : day
.

Definition next_day d := 
match d with
| sun => mon
| mon => tue
| tue => wed
| wed => thu
| thu => fri
| fri => sat
| sat => sun
end.

Compute next_day tue.

Theorem wed_after_tue : next_day tue = wed.
Proof.
  simpl. trivial.
Qed.
Print wed_after_tue.

Theorem day_never_repeats: 
  forall d, next_day d <> d.
Proof.
  intros d. (* introduce arb day *)
  destruct d. (* Case analysis *)
  all: simpl. all: discriminate.
Qed.

